package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record ChangeGateDTO(
        @NotEmpty(message = "gate is required")
        String gate,
        @NotEmpty(message = "terminal is required")
        String terminal
) {
}
