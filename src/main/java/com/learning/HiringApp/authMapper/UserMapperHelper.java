package com.learning.HiringApp.authMapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapperHelper {
    private final PasswordEncoder passwordEncoder;

    public UserMapperHelper(@Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Named("encodePassword")
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}