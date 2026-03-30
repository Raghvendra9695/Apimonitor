package com.apimonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class ApimonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApimonitorApplication.class, args);
	}

}
