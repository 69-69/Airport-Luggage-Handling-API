package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "message_board")
public class MessageBoard extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(
            name = "sender_username",
            referencedColumnName = "username",
            nullable = false
    )
    @JsonBackReference
    private User sender; // from

    @ManyToOne
    @JoinColumn(
            name = "recipient_username",
            referencedColumnName = "username",
            nullable = false
    )
    @JsonBackReference
    private User recipient; // to

    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    private String airline;

    // Constructors
    public MessageBoard() {
    }

    @Autowired
    public MessageBoard(String message, User sender, User recipient,
                        Boolean isRead, String airline) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
        this.isRead = isRead;
        this.airline = airline;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public Boolean getRead() {
        return isRead;
    }

    public String getAirline() {
        return airline;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

}
