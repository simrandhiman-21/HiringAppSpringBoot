package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class HiringAppSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HiringAppSpringBootApplication.class, args);

	}
}
