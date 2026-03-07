package com.assigndevelopers.airportluggagehandlingapi.dto;

import java.time.LocalDateTime;

public record MsgResponseDTO(
        String message,
        String to,
        String from,
        String airline,
        boolean isRead,
        LocalDateTime createdAt
) {
}
