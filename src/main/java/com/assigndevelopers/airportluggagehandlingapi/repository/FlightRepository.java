package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {
//    Optional<Flight> findByFlightNumber(String flightCode);
// Optional<Flight> findById(String flightId);

    Optional<Flight> findByFlightCode(String flightCode); // AA1234

    List<Flight> findByFlightCodeStartingWithAndGate(String airlineCode, String gate);

    Optional<Flight> findFlightByGate(String gate);

    void deleteByFlightCode(String flightCode);
}
