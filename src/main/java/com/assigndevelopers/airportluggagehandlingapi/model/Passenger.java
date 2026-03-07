package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Passenger extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Id
    @Column(length =  6, nullable = false, unique = true)
    private String identification; // Passport or Driver License Number

    @Column(length =  10, nullable = false, unique = true)
    private String ticketNumber;

    @Column(length =  6)
    private String flightCode; // AA1234

    private String status;

    // One Passenger have Many Bags
    @OneToMany(
            mappedBy = "passenger",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    @JsonManagedReference // This prevents infinite Loop
    private List<Bag> bags;

    public Passenger() {
    }

    @Autowired
    public Passenger(String firstName, String lastName, String identification, String flightCode, String status, String ticketNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identification = identification;
        this.flightCode = flightCode;
        this.status = status;
        this.ticketNumber = ticketNumber;
    }

    // One Passenger has One Ticket
//    @OneToOne(
//            mappedBy = "passenger",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private Ticket ticket;


//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

//    public Ticket getTicket() {
//        return ticket;
//    }

//    public void setTicket(Ticket ticket) {
//        this.ticket = ticket;
//    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public void setBags(List<Bag> bags) {
        this.bags = bags;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /*public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }*/

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightNumber) {
        this.flightCode = flightNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
