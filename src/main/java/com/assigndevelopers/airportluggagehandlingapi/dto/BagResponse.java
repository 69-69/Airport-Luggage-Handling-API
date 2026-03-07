package com.assigndevelopers.airportluggagehandlingapi.dto;

import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;

import java.time.LocalDateTime;

public record BagResponse(
        Long bagId,
        String location,
        String ticketNumber,
        Flight flight,
        LocalDateTime createdAt
) {}
