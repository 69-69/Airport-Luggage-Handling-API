package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> findAllByPassenger(Passenger passenger) {
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);
        return passengers;
    }


    public Optional<List<Passenger>> getAll() {
        List<Passenger> passengers = passengerRepository.findAll();
        return Optional.of(passengers);
    }

    public Optional<Passenger> findById(String id) {
        return passengerRepository.findById(id);
    }

    public Optional<Passenger> findByFlightCode(String ticketNumber) {
        return passengerRepository.findByFlightCode(ticketNumber);
    }

    // This is 6 digits Identification number from Passport or Driver License
    public Optional<Passenger> findByTicketNumber(String flightNumber) {
        return passengerRepository.findByTicketNumber(flightNumber);
    }

    // This is 6 digits Identification number from Passport or Driver License
    public Optional<Passenger> findByIdNumber(String flightNumber) {
        return passengerRepository.findByIdentification(flightNumber);
    }

    public Passenger save(PassengerDTO dto) {
        Passenger passenger = new Passenger();

        passenger.setFirstName(dto.firstName());
        passenger.setLastName(dto.lastName());
        passenger.setFlightCode(dto.flightCode());
        passenger.setStatus(dto.status());
        passenger.setIdentification(dto.idNumber());
        passenger.setTicketNumber(dto.ticketNumber());

        return passengerRepository.save(passenger);
    }

    Optional<Passenger> update(Passenger passenger) {
        return Optional.of(passengerRepository.save(passenger));
    }

    @Transactional
    public void delete(String id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Passenger with id: " + id + " deleted")
                );

        passengerRepository.delete(passenger);
    }
}
