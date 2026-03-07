package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePasswordDTO(
        @NotEmpty(message = "first login is required")
        boolean firstLogin,
        @NotEmpty(message = "New password is required")
        String newPassword
) {
}
