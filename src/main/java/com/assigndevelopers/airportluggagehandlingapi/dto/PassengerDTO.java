package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PassengerDTO(
        @Size(max = 6)
        String flightCode,

        @NotEmpty(message = "firstName is required")
        String firstName,

        @NotEmpty(message = "lastName is required")
        String lastName,

        @Size(max = 6)
        @NotEmpty(message = "idNumber is required")
        @Size(max = 10)
        String idNumber,

        @NotEmpty(message = "ticketNumber is required")
        String ticketNumber,

        @NotEmpty(message = "isRead is required")
        String status
) {

}
