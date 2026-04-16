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
    public User loginUser(String email, String rawPassword) {

        User user = userRepository.findByEmail(email);

        if (user != null) {

            boolean isMatch = passwordEncoder.matches(rawPassword, user.getPassword());


            System.out.println("DEBUG: Login attempt for email: " + email);
            System.out.println("DEBUG: Password Match Result: " + isMatch);

            if (isMatch) {
                return user;
            }
        } else {
            System.out.println("DEBUG: No user found in DB with email: " + email);
        }


        throw new RuntimeException("Invalid credentials");
    }
}