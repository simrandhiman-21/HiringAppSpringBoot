package com.learning.HiringApp.authDto;

import java.io.Serializable;

public record AuthResponse(
        String message
) implements Serializable {
}