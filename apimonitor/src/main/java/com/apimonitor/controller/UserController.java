package com.apimonitor.controller;

import com.apimonitor.entity.User;
import com.apimonitor.service.UserService;
import com.apimonitor.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println("Registering user: " + user.getEmail());
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        System.out.println("Login attempt for: " + user.getEmail());
        try {
            User dbUser = userService.loginUser(user.getEmail(), user.getPassword());

            if (dbUser != null) {
                String token = JwtUtil.generateToken(dbUser.getEmail());
                System.out.println("✅ Login Successful for: " + dbUser.getEmail());
                return ResponseEntity.ok(token);
            }
            return ResponseEntity.status(401).body("Invalid email or password");

        } catch (RuntimeException e) {

            System.out.println("❌ Login Failed: " + e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (Exception e) {
            System.out.println("🔥 Server Error: " + e.getMessage());
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}