package com.assigndevelopers.airportluggagehandlingapi.dto;

public class FlightDTO {
    private String airlineName;
    private String destination;
    private String flightCode;
    private String departureTime;
    private String terminal;
    private String gate;

    // Constructors
    public FlightDTO() {
    }

    public FlightDTO(String airlineName, String destination, String flightCode, String departureTime, String terminal, String gate) {
        this.airlineName = airlineName;
        this.destination = destination;
        this.flightCode = flightCode;
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

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
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

}
