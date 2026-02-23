package com.assigndevelopers.airportluggagehandlingapi.dto;

public class AuthResult {
    private boolean success;
    private UserDTO user; // present if success = true
    private String error; // present if success = false

    // Constructors
    public AuthResult() {
    }

    // Success Constructor
    public AuthResult(UserDTO user) {
        this.success = true;
        this.user = user;
    }

    // Failure Constructor
    public AuthResult(String error) {
        this.success = false;
        this.error = error;
    }

    // Getters & Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
