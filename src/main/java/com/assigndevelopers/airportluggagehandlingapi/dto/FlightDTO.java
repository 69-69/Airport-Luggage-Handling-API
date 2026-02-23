package com.assigndevelopers.airportluggagehandlingapi.dto;

import com.assigndevelopers.airportluggagehandlingapi.model.Ticket;

import java.util.List;

public class FlightDTO {
    private String airlineName;
    private String destination;
    private String flightId;
    private String flightNumber;
    private String departureTime;
    private String terminal;
    private String gate;

    // Constructors
    public FlightDTO() {
    }

    public FlightDTO(String airlineName, String destination, String flightId, String flightNumber, String departureTime, String terminal, String gate) {
        this.airlineName = airlineName;
        this.destination = destination;
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.terminal = terminal;
        this.gate = gate;
    }

    // Getters & Setters
    // Getters and setters
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

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
}
