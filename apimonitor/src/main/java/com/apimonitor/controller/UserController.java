package com.apimonitor.controller;

import com.apimonitor.entity.User;
import com.apimonitor.service.UserService;
import com.apimonitor.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = userService.loginUser(user.getEmail(), user.getPassword());
        return JwtUtil.generateToken(dbUser.getEmail());
    }
}