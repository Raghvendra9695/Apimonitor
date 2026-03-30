package com.apimonitor.service;

import com.apimonitor.entity.ApiLog;
import com.apimonitor.repository.ApiLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogService {

    @Autowired
    private ApiLogRepository apiLogRepository;

    public List<ApiLog> getAllLogs() {
        return apiLogRepository.findAll();
    }

    public List<ApiLog> getLogsByApiId(Long apiId) {
        return apiLogRepository.findAll()
                .stream()
                .filter(log -> log.getApi().getId().equals(apiId))
                .toList();
    }
}