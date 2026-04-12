package com.apimonitor.controller;

import com.apimonitor.entity.ApiLog;
import com.apimonitor.service.ApiLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class ApiLogController {

    @Autowired
    private ApiLogService apiLogService;

    //  Get all logs
    @GetMapping
    public List<ApiLog> getAllLogs() {
        return apiLogService.getAllLogs();
    }

    //  Get logs by API ID
    @GetMapping("/{apiId}")
    public List<ApiLog> getLogsByApi(@PathVariable Long apiId) {
        return apiLogService.getLogsByApiId(apiId);
    }
}