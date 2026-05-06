package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Flight extends BaseEntity {

    @Column(nullable = false)
    private String airlineName;

    @Column(nullable = false)
    private String destination;

    @Id
    @Column(length = 6, nullable = false/*, unique = true*/)
    private String flightCode; // AA1234

    private String departureTime;

    @Column(nullable = false)
    private String terminal;

    @Column(nullable = false, unique = true)
    private String gate;

    // @Enumerated(EnumType.STRING)
    private String status;

    // new here
    @OneToMany(
            mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference("flight-bags")
    private List<Bag> bags;

    // Constructors
    public Flight() {
    }

    @Autowired
    public Flight(String airlineName, String destination, String flightCode,
                  String departureTime, String terminal, String gate,
                  List<Bag> bags, String status) {
        this.airlineName = airlineName;
        this.destination = destination;
        this.status = status;
        this.flightCode = flightCode;
        this.departureTime = departureTime;
        this.terminal = terminal;
        this.gate = gate;
        this.bags = bags;
    }

//    One Flight has Many Tickets
//    @OneToMany(
//            mappedBy = "flight",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JsonManagedReference // This prevents infinite Loop
//    private List<String> tickets;

    // Getters and setters

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightNumber) {
        this.flightCode = flightNumber;
    }
}
