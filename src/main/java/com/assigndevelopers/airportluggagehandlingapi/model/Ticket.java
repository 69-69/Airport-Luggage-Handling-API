package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity(name = "flight_tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticketNumber;

    // Many Tickets have One Flight
    @ManyToOne
    @JoinColumn(
            name = "flight_id", // Joine/Linked by "flight_id"
            nullable = false
    )
    @JsonBackReference // Prevents Child class (Ticket) from serializing Parent class (Flight)
    private Flight flight;

    // Many Tickets have One Passenger
    @ManyToOne
    @JoinColumn(
            name = "passenger_id",
            nullable = false
    )
    private Passenger passenger;

    // Constructors
    public Ticket() {
    }

    @Autowired
    public Ticket(String ticketNumber, Flight flight, Passenger passenger) {
        this.ticketNumber = ticketNumber;
        this.flight = flight;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
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
