package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public record FlightDTO(

        @NotBlank(message = "Airline name is required")
        String airlineName,

        @NotBlank(message = "Destination is required")
        String destination,

        @NotBlank(message = "Flight code is required")
        String flightCode,

        String departureTime,

        @NotBlank(message = "Terminal is required")
        String terminal,

        @NotBlank(message = "Gate is required")
        String gate

//        List<BagDTO> bags,

//        boolean isBoarding
) {}