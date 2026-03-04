package com.assigndevelopers.airportluggagehandlingapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "_user")
public class User {
    // Using generated security password: 976adece-732e-41e4-b6f5-b584a9f00f20

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Column(nullable = false)
    private String role;

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    private String username;

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
    @OneToMany(mappedBy = "sender")
    @JsonManagedReference
    private List<MessageBoard> sentMessages;

    /*
     *  ONE-TO-MANY (Received Messages)
     */
    @OneToMany(mappedBy = "recipient")
    @JsonManagedReference
    private List<MessageBoard> receivedMessages;

    @Column(nullable = false)
    private boolean isFirstLogin;

    // Constructors
    public User() {
    }

    @Autowired
    public User(String role, String username, String password, boolean isFirstLogin, UserProfile profile) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.isFirstLogin = isFirstLogin;
        this.profile = profile;
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

    public List<MessageBoard> getSentMessages() {
        return sentMessages;
    }

    public List<MessageBoard> getReceivedMessages() {
        return receivedMessages;
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
