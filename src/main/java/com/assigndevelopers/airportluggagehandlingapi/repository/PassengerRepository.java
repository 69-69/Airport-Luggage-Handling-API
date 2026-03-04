package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findByTicketNumber(String ticketTicketNumber);

    // Optional<Passenger> findByFlightNumber(String flightNumber);

    Optional<Passenger> findByIdentification(String idNumber);
}
