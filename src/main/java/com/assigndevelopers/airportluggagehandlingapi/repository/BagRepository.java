package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BagRepository extends JpaRepository<Bag, Long> {
    // Optional<Bag> findById(long bagId);

    // Optional<List<Bag>> getAll();

    Optional<List<Bag>> findByPassengerIdentification(String ticketNumber);

    List<Bag> findByPassenger(Passenger passenger);
}
