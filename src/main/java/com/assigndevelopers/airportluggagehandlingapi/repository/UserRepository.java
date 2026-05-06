package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByRole(String role);

    Optional<User> findByProfileEmail(String email);

    Optional<User> findByProfilePhone(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByProfileEmailAndPassword(String email, String password);
}
