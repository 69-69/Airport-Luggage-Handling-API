package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record PassengerDTO(
        @Size(max = 6)
        String flightCode,

        @NotEmpty(message = "first name is required")
        String firstName,

        @NotEmpty(message = "last name is required")
        String lastName,

        @Size(max = 6)
        @NotEmpty(message = "id number is required")
        String idNumber,

        @NotEmpty(message = "ticket number is required")
        @Size(max = 10)
        String ticketNumber,

        @NotEmpty(message = "isRead is required")
        String status
) {

}
