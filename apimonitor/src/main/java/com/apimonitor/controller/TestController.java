package com.apimonitor.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/secure")
    public String secureApi() {
        return "This is secured API 🔐";
    }
}