package com.learning.HiringApp.authDto;

public record VerifyOtpRequest(
        String email,
        String otp
) {
}