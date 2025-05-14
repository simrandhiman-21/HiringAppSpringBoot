package com.learning.HiringApp.config;

import com.learning.HiringApp.authEntity.User;
import com.learning.HiringApp.jwt.JwtAuthFilter;
import com.learning.HiringApp.repository.UserDetailsRepository;
import com.learning.HiringApp.service.CustomUserDetailService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {
    private final CustomUserDetailService customUserDetailsService;
    private final UserDetailsRepository userDetailsRepository;

    public JwtSecurityConfig(final CustomUserDetailService customUserDetailsService,
                             final UserDetailsRepository userDetailsRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Bean(name = "jwtSecurityFilterChain")
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authReq -> authReq
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner createAdmin(PasswordEncoder passwordEncoder) {
        return args -> {
            if (userDetailsRepository.findByEmail("rishav7381@gmail.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("rishav7381@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ADMIN");
                userDetailsRepository.save(admin);
            }
        };
    }
}