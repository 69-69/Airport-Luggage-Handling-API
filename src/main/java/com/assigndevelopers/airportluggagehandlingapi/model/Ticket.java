/*
package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "flight_tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_number", length =  10, nullable = false, unique = true)
    private String number;

    // Many Tickets have One Flight
    @ManyToOne
    @JoinColumn(
            name = "flight_id", // Joine/Linked by "flight_id"
            nullable = false
    )
    @JsonBackReference // Prevents Child class (Ticket) from serializing Parent class (Flight)
    private Flight flight;

    // One Ticket have One Passenger
    @OneToOne
    @JoinColumn(
            name = "identification",
            referencedColumnName = "identification",
            nullable = false
    )
    private Passenger passenger;

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

    // Constructors
    public Ticket() {
    }

    @Autowired
    public Ticket(String number, Flight flight, Passenger passenger, String createdAt, String updatedAt) {
        this.number = number;
        this.flight = flight;
        this.passenger = passenger;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String ticketNumber) {
        this.number = ticketNumber;
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

}
*/
