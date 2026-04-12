package com.apimonitor.controller;

import com.apimonitor.entity.Api;
import com.apimonitor.entity.ApiLog;
import com.apimonitor.repository.ApiLogRepository;
import com.apimonitor.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/apis")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ApiLogRepository apiLogRepository;

    // 1. Add new API
    @PostMapping
    public Api addApi(@RequestBody Api api) {
        return apiService.addApi(api);
    }

    // 2. Get all APIs
    @GetMapping
    public List<Map<String, Object>> getAllApis() {
        List<Api> apis = apiService.getAllApis();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Api api : apis) {
            Map<String, Object> apiData = new HashMap<>();
            apiData.put("id", api.getId());
            apiData.put("name", api.getName());
            apiData.put("url", api.getUrl());
            apiData.put("method", api.getMethod());

            Optional<ApiLog> latestLog = apiLogRepository.findTopByApiIdOrderByTimestampDesc(api.getId());

            if (latestLog.isPresent()) {
                apiData.put("status", latestLog.get().getStatus());
                apiData.put("latency", latestLog.get().getResponseTime());
            } else {
                apiData.put("status", "PENDING");
                apiData.put("latency", 0);
            }
            response.add(apiData);
        }
        return response;
    }


    @DeleteMapping("/{id}")
    public String deleteApi(@PathVariable Long id) {
        apiService.deleteApi(id);
        return "API deleted successfully";
    }
}