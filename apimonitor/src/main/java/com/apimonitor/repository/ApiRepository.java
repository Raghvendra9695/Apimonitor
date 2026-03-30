package com.apimonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apimonitor.entity.Api;

public interface ApiRepository extends JpaRepository<Api, Long> {
}