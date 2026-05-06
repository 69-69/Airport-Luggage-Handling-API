package com.assigndevelopers.airportluggagehandlingapi.dto;

import java.time.LocalDateTime;

public record MsgResponseDTO(
        Long id,
        String message,
        String to,
        String fromRole,
        String fromUsername,
        String airline,
        boolean isRead,
        LocalDateTime timestamp

) {
}
