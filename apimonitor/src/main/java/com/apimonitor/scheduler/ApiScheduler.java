package com.apimonitor.scheduler;
import com.apimonitor.entity.*;
import com.apimonitor.repository.*;
import com.apimonitor.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApiScheduler {

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApiLogRepository apiLogRepository;

    @Autowired
    private EmailService emailService;

    private RestTemplate restTemplate = new RestTemplate();

    // ALERT CONTROL
    private Map<Long, Long> lastAlertTime = new HashMap<>();
    private static final long ALERT_COOLDOWN = 5 * 60 * 1000; // 5 minutes

    @Scheduled(fixedRate = 60000)
    public void monitorApis() {

        System.out.println("🔄 Scheduler running...");

        List<Api> apis = apiRepository.findAll();

        for (Api api : apis) {

            long start = System.currentTimeMillis();

            try {
                ResponseEntity<String> response =
                        restTemplate.getForEntity(api.getUrl(), String.class);

                long time = System.currentTimeMillis() - start;
                int status = response.getStatusCode().value();

                // 🔹 SAVE LOG
                ApiLog log = new ApiLog();
                log.setStatus(status);
                log.setResponseTime(time);
                log.setTimestamp(LocalDateTime.now());
                log.setApi(api);

                apiLogRepository.save(log);

                long currentTime = System.currentTimeMillis();
                Long lastTime = lastAlertTime.get(api.getId());

                // 🚨 ALERT: API DOWN
                if (status != 200) {
                    if (lastTime == null || (currentTime - lastTime) > ALERT_COOLDOWN) {

                        String msg = "🚨 API DOWN: " + api.getUrl() + " | Status: " + status;

                        System.out.println(msg);

                        emailService.sendAlert(
                                "your_email@gmail.com",
                                "API DOWN ALERT 🚨",
                                msg
                        );

                        lastAlertTime.put(api.getId(), currentTime);
                    }
                }

                // ⚠️ ALERT: SLOW API
                if (time > 2000) {
                    if (lastTime == null || (currentTime - lastTime) > ALERT_COOLDOWN) {

                        String msg = "⚠️ API SLOW: " + api.getUrl() + " | Time: " + time + " ms";

                        System.out.println(msg);

                        emailService.sendAlert(
                                "your_email@gmail.com",
                                "API SLOW ALERT ⚠️",
                                msg
                        );

                        lastAlertTime.put(api.getId(), currentTime);
                    }
                }

            } catch (Exception e) {

                // 🔹 SAVE ERROR LOG
                ApiLog log = new ApiLog();
                log.setStatus(500);
                log.setResponseTime(0);
                log.setTimestamp(LocalDateTime.now());
                log.setApi(api);

                apiLogRepository.save(log);

                long currentTime = System.currentTimeMillis();
                Long lastTime = lastAlertTime.get(api.getId());

                // 🚨 ALERT: API FAILED
                if (lastTime == null || (currentTime - lastTime) > ALERT_COOLDOWN) {

                    String msg = "🚨 API FAILED: " + api.getUrl();

                    System.out.println(msg);

                    emailService.sendAlert(
                            "your_email@gmail.com",
                            "API FAILED ALERT 🚨",
                            msg
                    );

                    lastAlertTime.put(api.getId(), currentTime);
                }
            }
        }
    }
}