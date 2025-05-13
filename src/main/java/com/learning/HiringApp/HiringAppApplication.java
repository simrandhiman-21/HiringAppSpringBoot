package com.learning.HiringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.learning.HiringApp")
public class HiringAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(HiringAppApplication.class, args);
	}
}
