package com.assigndevelopers.airportluggagehandlingapi.dto;

public record AuthResultDTO(
        boolean success,
        UserDTO user,   // present if success = true
        String error    // present if success = false
) {

    // Factory methods for convenience
    public static AuthResultDTO success(UserDTO user) {
        return new AuthResultDTO(true, user, null);
    }

    public static AuthResultDTO failure(String error) {
        return new AuthResultDTO(false, null, error);
    }
}