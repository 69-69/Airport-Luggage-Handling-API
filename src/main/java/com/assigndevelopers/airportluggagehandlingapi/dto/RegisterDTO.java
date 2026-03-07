package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDTO(
        @NotEmpty(message="String username is required")
        String username,

        @NotEmpty(message="String password is required")
        String password,

        @NotEmpty(message="String email is required")
        String email,

        @NotEmpty(message="String phone is required")
        String phone,

        @NotEmpty(message="String role is required")
        String role,

        @NotEmpty(message="String firstname is required")
        String firstname,

        @NotEmpty(message="String lastname is required")
        String lastname,

        String airline

) {
}
