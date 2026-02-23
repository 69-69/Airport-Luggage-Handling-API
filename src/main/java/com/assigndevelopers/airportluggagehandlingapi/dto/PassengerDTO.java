package com.assigndevelopers.airportluggagehandlingapi.dto;

public class PassengerDTO {

    private String firstName;
    private String lastName;
    private String idNumber;
    private String ticketNumber;
    private String flightNumber;
    private String status;

    public PassengerDTO() {
    }

    public PassengerDTO(String firstName, String lastName, String idNumber, String ticketNumber, String flightNumber, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.ticketNumber = ticketNumber;
        this.flightNumber = flightNumber;
        this.status = status;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
