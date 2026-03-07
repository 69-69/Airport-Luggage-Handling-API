package com.assigndevelopers.airportluggagehandlingapi.dto;

public record UserDTO(String username,
                      String email,
                      String phone,
                      String role,
                      String firstname,
                      String lastname,
                      String airline,
                      boolean firstLogin) {
}
