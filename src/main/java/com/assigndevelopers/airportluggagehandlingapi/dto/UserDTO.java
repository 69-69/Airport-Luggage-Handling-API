package com.assigndevelopers.airportluggagehandlingapi.dto;

public class UserDTO {
    private String username;
    private String email;

    private String phone;

//    @EnumValidator
    private String role;
    private String firstname;
    private String lastname;
    private String airline;
    private boolean firstLogin;

    // Constructors
    public UserDTO() {}

    public UserDTO(String role, String username, boolean firstLogin, String email, String phone, String firstname, String lastname, String airline) {
        this.role = role;
        this.username = username;
        this.firstLogin = firstLogin;
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.airline = airline;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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

    public boolean isFirstLogin() {
        return firstLogin;
    }
    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
