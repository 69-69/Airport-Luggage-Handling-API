package com.assigndevelopers.airportluggagehandlingapi.dto;

public record AuthResultDTO(
        boolean success,
        UserDTO user,   // present if success = true
        PassengerDTO passenger,   // present if success = true
        String error    // present if success = false
) {

    // Factory methods for convenience
    public static AuthResultDTO success(UserDTO user) {
        return new AuthResultDTO(true, user, null, null);
    }

    public static AuthResultDTO successPassenger(PassengerDTO passenger) {
        return new AuthResultDTO(true, null, passenger, null);
    }

    public static AuthResultDTO failure(String error) {
        return new AuthResultDTO(false, null, null, error);
    }

    public static AuthResultDTO allUsers(UserDTO user) {
        return new AuthResultDTO(false, user, null, null);
    }
}