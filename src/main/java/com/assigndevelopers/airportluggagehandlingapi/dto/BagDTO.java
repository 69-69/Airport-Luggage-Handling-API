package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BagDTO(
        @NotNull
        @Digits(integer = 6, fraction = 0, message = "bagId must be 6 digits")
        Long bagId,

        @NotEmpty(message = "location or isRead is required")
        String location,

        @NotEmpty(message = "10 digits flight number is required")
        String flightCode,

        @NotEmpty(message = "ticket number is required")
        String ticketNumber
) {
}
