package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BagRepository extends JpaRepository<Bag, Long> {
    // Optional<Bag> findById(long id);

    // Optional<List<Bag>> getAll();

    Optional<Bag> findByTicketNumber(String ticketNumber);
}
