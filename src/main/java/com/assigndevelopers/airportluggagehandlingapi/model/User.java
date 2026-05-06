package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "_user")
public class User {

    @Column(nullable = false)
    private String role;

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;
    /*
     *  ONE-TO-ONE WITH USER PROFILE
     */
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private UserProfile profile;

    /*
     *  ONE-TO-MANY (Sent Messages)
     */
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("sender-username")
    private List<Message> sentMessages;

    /*
     *  ONE-TO-MANY (Received Messages)
    @OneToMany(mappedBy = "recipient")
    @JsonManagedReference("recipient-username")
    private List<Message> receivedMessages;*/

    @Column(nullable = false)
    private boolean isFirstLogin;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Column(nullable = false)
    private boolean isActive;

    // Constructors
    public User() {
    }

    @Autowired
    public User(String role, String username, String password, boolean isFirstLogin, UserProfile profile, boolean isActive) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.isFirstLogin = isFirstLogin;
        this.profile = profile;
        this.isActive = isActive;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
        if (profile != null) {
            profile.setUser(this);
        }
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }
}
