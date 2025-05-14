package com.learning.HiringApp.authDto;

public record VerifyOtpResponse(
        boolean success,
        String token
) {
}