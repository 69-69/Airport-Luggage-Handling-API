package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String airlineName;

    @Column(nullable = false)
    private String destination;

    /*@Column(nullable = false)
    private String flightNumber; // 123456*/

    @Column(length =  6, nullable = false)
    private String flightCode; // AA1234

    private String departureTime;

    @Column(nullable = false)
    private String terminal;

    @Column(nullable = false, unique = true)
    private String gate;

    // One Flight has Many Tickets
    @OneToMany(
            mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference // This prevents infinite Loop
    private List<Ticket> tickets;

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

    // new here
    @OneToMany(
            mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Bag> bags;


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

    @Column(nullable = false)
    private String createdAt;

    private String updatedAt;

    // Constructors
    public Flight() {
    }

    @Autowired
    public Flight(String airlineName, String destination, String flightCode, String departureTime, String terminal, String gate, String createdAt, String updatedAt, List<Bag> bags) {
        this.airlineName = airlineName;
        this.destination = destination;
//        this.flightNumber = flightNumber;
        this.flightCode = flightCode;
        this.departureTime = departureTime;
        this.terminal = terminal;
        this.gate = gate;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.bags=bags;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    /*public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightId) {
        this.flightNumber = flightId;
    }*/

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightNumber) {
        this.flightCode = flightNumber;
    }
}
