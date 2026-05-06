package com.assigndevelopers.airportluggagehandlingapi.repository;

import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, String> {

    Optional<Passenger> findByFlightCode(String ticketTicketNumber);

     Optional<Passenger> findByTicketNumber(String flightNumber);

    Optional<Passenger> findByIdentification(String idNumber);

    Optional<Passenger> findByIdentificationAndTicketNumber(String identification, String ticketNumber);
}
