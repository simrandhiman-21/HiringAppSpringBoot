package com.learning.HiringApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/candidates/**").permitAll()
//                        .requestMatchers("/api/bank-info/**").permitAll()
//                        .requestMatchers("/api/educational-info/**").permitAll()
//                        .requestMatchers("/api/documents/**").permitAll()
//                        .requestMatchers("/test/**").permitAll()
//                        .requestMatchers("/api/test/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(csrf -> csrf.disable());
//        return http.build();
//    }
}
