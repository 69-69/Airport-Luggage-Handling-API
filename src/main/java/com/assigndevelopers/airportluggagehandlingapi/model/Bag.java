package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length =  6, nullable = false)
    private String bagId;

    @Column(length =  10, nullable = false)
    private String ticketNumber;

    private String location; // Bag Status

    // Bags are linked to a ticket (Many bags can belong to one ticket)
    @ManyToOne
    @JoinColumn(
            name = "passenger_id",
            nullable = false
    )
    @JsonBackReference // Prevents Child class (Bag) from serializing Parent class (Passenger)
    private Passenger passenger;

    // Constructors
    public Bag() {
    }

    @Autowired
    public Bag(String ticketNumber, String location) {
        this.ticketNumber = ticketNumber;
        this.location = location;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String status) {
        this.location = location;
    }
}