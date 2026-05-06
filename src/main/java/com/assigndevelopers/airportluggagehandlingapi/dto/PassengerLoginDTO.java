package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record PassengerLoginDTO(
        @NotEmpty(message = "Identification is required (passport or driver license)")
        String identification,
        @NotEmpty(message = "Ticket is required")
        String ticket
) {
}
