package com.assigndevelopers.airportluggagehandlingapi.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(length = 2, nullable = false)
    private String airline;

    @OneToOne
    @JoinColumn(
            name = "username",
            referencedColumnName = "username",
            nullable = false,
            unique = true
    )
    private User user;

    @Column(nullable = false)
    private String createdAt;

    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Constructors
    public UserProfile(){}

    @Autowired
    public UserProfile(String email, String phone, String firstname, String lastname, String airline, String createdAt, String updatedAt) {
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
        this.airline = airline;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
