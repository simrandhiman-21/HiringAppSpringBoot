package com.learning.HiringApp.security.authDto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequest(
        @NotBlank String email,
        @NotBlank String otp,
        @Size(min = 6, max = 30) String newPassword
) {
}
