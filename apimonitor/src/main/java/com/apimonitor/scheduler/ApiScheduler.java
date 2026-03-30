package com.apimonitor.scheduler;

import com.apimonitor.entity.*;
import com.apimonitor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApiScheduler {

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApiLogRepository apiLogRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 60000)
    public void monitorApis() {

        List<Api> apis = apiRepository.findAll();

        for (Api api : apis) {

            long start = System.currentTimeMillis();

            try {
                ResponseEntity<String> response =
                        restTemplate.getForEntity(api.getUrl(), String.class);

                long time = System.currentTimeMillis() - start;

                ApiLog log = new ApiLog();
                log.setStatus(response.getStatusCode().value());
                log.setResponseTime(time);
                log.setTimestamp(LocalDateTime.now());
                log.setApi(api);

                apiLogRepository.save(log);

            } catch (Exception e) {

                ApiLog log = new ApiLog();
                log.setStatus(500);
                log.setResponseTime(0);
                log.setTimestamp(LocalDateTime.now());
                log.setApi(api);

                apiLogRepository.save(log);
            }
        }
    }
}