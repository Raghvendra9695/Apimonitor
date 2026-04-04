package com.apimonitor.service;
import com.apimonitor.entity.Api;
import com.apimonitor.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private ApiRepository apiRepository;

    // Add API
    public Api addApi(Api api) {
        return apiRepository.save(api);
    }

    // Get all APIs
    public List<Api> getAllApis() {
        return apiRepository.findAll();
    }

    // Delete API
    public void deleteApi(Long id) {
        apiRepository.deleteById(id);
    }
}