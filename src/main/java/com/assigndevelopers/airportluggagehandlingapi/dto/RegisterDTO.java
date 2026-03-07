package com.assigndevelopers.airportluggagehandlingapi.dto;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDTO(
        @NotEmpty(message="username is required")
        String username,

        @NotEmpty(message="password is required")
        String password,

        @NotEmpty(message="email is required")
        String email,

        @NotEmpty(message="phone is required")
        String phone,

        @NotEmpty(message="role is required")
        String role,

        @NotEmpty(message="first name is required")
        String firstName,

        @NotEmpty(message="last name is required")
        String lastName,

        String airline

) {
}
