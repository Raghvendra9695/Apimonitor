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

    /**
     * Naya user register karne ke liye
     */
    public User registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        System.out.println("DEBUG: Registering user with email: " + user.getEmail());
        return userRepository.save(user);
    }

    /**
     * Login logic with Debugging logs
     */

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }
}