package com.apimonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apimonitor.entity.User;
import com.apimonitor.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register User
    public User registerUser(User user) {

        String rawPassword = user.getPassword().trim();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        user.setPassword(encodedPassword);

        System.out.println("✅ Registering user: " + user.getEmail());

        return userRepository.save(user);
    }

    // Login User
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            System.out.println("❌ User not found: " + email);
            throw new RuntimeException("Invalid credentials");
        }

        String inputPassword = password.trim();

        System.out.println("📩 Login attempt: " + email);
        System.out.println("🔑 Input Password: [" + inputPassword + "]");
        System.out.println("🔒 DB Hash: " + user.getPassword());

        boolean isMatch = passwordEncoder.matches(inputPassword, user.getPassword());

        System.out.println("✅ Password Match Result: " + isMatch);

        if (isMatch) {
            return user;
        }

        throw new RuntimeException("Invalid credentials");
    }
}