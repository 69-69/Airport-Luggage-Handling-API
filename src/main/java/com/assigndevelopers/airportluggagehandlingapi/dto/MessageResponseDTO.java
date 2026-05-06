package com.assigndevelopers.airportluggagehandlingapi.dto;

public record MessageResponseDTO(
        Long id,
        String message,
        String airline,
        String recipient,
        String senderUsername,
        boolean isRead
) {}
