package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record MessageDTO(
        @NotEmpty(message = "message is required")
        String message,
        @NotEmpty(message = "recipient is required")
        String to,
        @NotEmpty(message = "sender role is required")
        String fromRole,
        @NotEmpty(message = "sender username is required")
        String fromUsername,
        @NotEmpty(message = "airline is required")
        String airline,
        String isRead
) {
}
