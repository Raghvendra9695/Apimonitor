package com.apimonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apimonitor.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}