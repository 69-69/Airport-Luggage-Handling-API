package com.assigndevelopers.airportluggagehandlingapi.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String role;
    private String firstname;
    private String lastname;
    private String airline;

    // Constructors
    public RegisterRequest() {
    }

    public RegisterRequest(String username, String password, String email, String role, String firstname, String lastname, String airline) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
        this.airline = airline;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
