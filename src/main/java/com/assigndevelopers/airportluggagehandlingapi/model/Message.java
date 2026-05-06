package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "message_board")
public class Message extends BaseEntity {
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
//    @JsonIgnore
    @JsonBackReference("message-sender")
    private User sender; // from

    /*@ManyToOne
    @JoinColumn(
            name = "recipient_username",
            referencedColumnName = "username",
            nullable = false
    )
//    @JsonIgnore
    @JsonBackReference("message-recipient")
    private User recipient; // to*/
    // Recipient: represent Role
    private String recipient; // to

    @Column(nullable = false)
    private boolean isRead;

    @Column(nullable = false)
    private String airline;

    // Constructors
    public Message() {
    }

    @Autowired
    public Message(String message, User sender, String recipient,
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

    public String getRecipient() {
        return recipient;
    }

    public boolean getRead() {
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

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

}
