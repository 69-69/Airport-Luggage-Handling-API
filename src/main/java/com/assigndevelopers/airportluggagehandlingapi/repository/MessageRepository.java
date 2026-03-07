package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.MessageBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<MessageBoard, Long> {
    void deleteById(Long id);

    List<MessageBoard> findBySenderRole(String role);

    List<MessageBoard> findByRecipientRole(String recipientRole);

}
