package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Bag {

    @Id // This ID is Generated & Sent from the UI
    @Column(length = 6, nullable = false, unique = true)
    private Long id; // 123456

    /* @Column(length = 6, nullable = false, unique = true)
    private String bagId;

    @Column(length = 10, nullable = false, unique = true)
    private String ticketNumber;*/

    @Column(nullable = false)
    private String location; // Bag Status

    @Column(nullable = false)
    private String createdAt;

    private String updatedAt;

    // Bags are linked to a ticket (Many bags can belong to one ticket)
    @ManyToOne
    @JoinColumn(
            name = "passenger_id",
            nullable = false
    )
    @JsonBackReference // Prevents Child class (Bag) from serializing Parent class (Passenger)
    private Passenger passenger;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    // New here
    @ManyToOne
    @JoinColumn(
            name = "flight_id",
            nullable = false
    )
    @JsonBackReference
    private Flight flight;

    // Constructors
    public Bag() {
    }

    @Autowired
    public Bag(Long bagId, String location, String createdAt, String updatedAt, Flight flight) {
        // this.ticketNumber = ticketNumber;
        this.location = location;
        this.id = bagId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.flight = flight;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String status) {
        this.location = location;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    /*public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }*/
}