package com.assigndevelopers.airportluggagehandlingapi.dto;

public class MessageDTO {
    private String message;
    private String to;
    private String from;
    private String status;
    private String airline;

    // Constructors
    public MessageDTO() {
    }

    public MessageDTO(String message, String to, String from, String status, String airline) {
        this.message = message;
        this.to = to;
        this.from = from;
        this.status = status;
        this.airline = airline;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
