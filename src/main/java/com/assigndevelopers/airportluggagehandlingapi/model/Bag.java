package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Bag extends  BaseEntity {

    @Id // This ID is Generated & Sent from the UI
    @Column(length = 6, nullable = false, unique = true)
    private Long id; // 123456

//    @Column(length = 6, nullable = false, unique = true)
//    private String bagId;
//
//    @Column(length = 10, nullable = false, unique = true)
//    private String ticketNumber;

    @Column(nullable = false)
    private String location; // Bag Status

    // Bags are linked to a ticket (Many bags can belong to one ticket)
    @ManyToOne
    @JoinColumn(
            name = "ticketNumber",
            referencedColumnName = "ticketNumber",
            nullable = false
    )
//    @JsonBackReference // Prevents Child class (Bag) from serializing Parent class (Passenger)
    @JsonIgnore
    private Passenger passenger;

    // New here
    @ManyToOne
    @JoinColumn(
            name = "flight_id",
            nullable = false
    )
//    @JsonBackReference
    @JsonIgnore
    private Flight flight;

    // Constructors
    public Bag() {
    }

    @Autowired
    public Bag(Long bagId, String location, Flight flight) {
        this.location = location;
        this.id = bagId;
        this.flight = flight;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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

    public void setLocation(String location) {
        this.location = location;
    }

}