package com.apimonitor.controller;

import com.apimonitor.entity.Api;
import com.apimonitor.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apis")
public class ApiController {

    @Autowired
    private ApiService apiService;

    // Add new API
    @PostMapping
    public Api addApi(@RequestBody Api api) {
        return apiService.addApi(api);
    }

    //  Get all APIs
    @GetMapping
    public List<Api> getAllApis() {
        return apiService.getAllApis();
    }

    //  Delete API
    @DeleteMapping("/{id}")
    public String deleteApi(@PathVariable Long id) {
        apiService.deleteApi(id);
        return "API deleted successfully";
    }
}