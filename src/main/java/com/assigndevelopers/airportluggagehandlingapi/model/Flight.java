package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Flight extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false, unique = true)
    private String airlineName;

    @Column(nullable = false)
    private String destination;

    @Id
    @Column(length = 6, nullable = false)
    private String flightCode; // AA1234

    private String departureTime;

    @Column(nullable = false)
    private String terminal;

    @Column(nullable = false, unique = true)
    private String gate;

    private boolean isBoarding;

    // new here
    @OneToMany(
            mappedBy = "flight",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    @JsonManagedReference
    private List<Bag> bags;

    // Constructors
    public Flight() {
    }

    @Autowired
    public Flight(String airlineName, String destination, String flightCode,
                  String departureTime, String terminal, String gate,
                  List<Bag> bags, boolean isBoarding) {
        this.airlineName = airlineName;
        this.destination = destination;
        this.isBoarding = isBoarding;
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

    public boolean getIsBoarding() {
        return isBoarding;
    }

    public void setIsBoarding(boolean isBoarding) {
        this.isBoarding = isBoarding;
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
