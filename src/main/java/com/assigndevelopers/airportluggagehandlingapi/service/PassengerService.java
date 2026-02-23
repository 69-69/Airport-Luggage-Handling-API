package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.repository.PassengerRepository;
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

    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    public Optional<Passenger> findByTicketNumber(String ticketNumber) {
        return passengerRepository.findByTicketNumber(ticketNumber);
    }

    // This is 6 digits Identification number from Passport or Driver License
    public Passenger findByIdNumber(String flightNumber) {
        Optional<Passenger> passenger = passengerRepository.findByIdentification(flightNumber);
        return passenger.orElse(null);
    }

    public void save(PassengerDTO passenger) {
        Passenger newPassenger = new Passenger();

        newPassenger.setFirstName(passenger.getFirstName());
        newPassenger.setLastName(passenger.getLastName());
        newPassenger.setFlightNumber(passenger.getFlightNumber());
        newPassenger.setStatus(passenger.getStatus());
        newPassenger.setIdentification(String.valueOf(passenger.getIdNumber()));
        newPassenger.setIdentification(String.valueOf(passenger.getIdNumber()));

        passengerRepository.save(newPassenger);
    }

    Optional<Passenger> update(Passenger passenger) {
        return Optional.of(passengerRepository.save(passenger));
    }

    public void delete(Passenger passenger) {
        passengerRepository.delete(passenger);
    }
}
