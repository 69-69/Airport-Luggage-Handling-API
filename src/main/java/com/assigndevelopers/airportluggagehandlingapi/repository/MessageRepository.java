package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Message;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteById(@NonNull Long id);

    List<Message> findBySenderRole(String role);

    /// Recipient: Represent Role
    List<Message> findByRecipient(String recipientRole);

    Optional<Message> findByMessage(String message);
}
