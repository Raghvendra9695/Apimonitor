package com.apimonitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apimonitor.entity.ApiLog;

import java.util.List;
import java.util.Optional;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
    List<ApiLog> findByApiId(Long apiId);

    Optional<ApiLog> findTopByApiIdOrderByTimestampDesc(Long apiId);
}