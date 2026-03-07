package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record MessageDTO(
        @NotEmpty(message = "message is required")
        String message,
        @NotEmpty(message = "recipient is required")
        String to,
        @NotEmpty(message = "sender is required")
        String from,
        @NotEmpty(message = "airline is required")
        String airline,
        String status
) {
}
