package com.assigndevelopers.airportluggagehandlingapi.dto;

import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record FlightDTO(
        @NotEmpty(message = "Airline name is required")
        String airlineName,
        @NotEmpty(message = "Destination is required")
        String destination,
        @NotEmpty(message = "Destination is required")
        String flightCode,
        @NotEmpty(message = "Destination is required")
        String departureTime,
        @NotEmpty(message = "Departure time is required")
        String terminal,
        @NotEmpty(message = "Terminal is required")
        String gate,
        List<Bag> bags,
        boolean isBoarding
) {

}
