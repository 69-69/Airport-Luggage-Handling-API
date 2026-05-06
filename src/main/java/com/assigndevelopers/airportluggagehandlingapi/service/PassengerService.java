package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResultDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerStatus;
import com.assigndevelopers.airportluggagehandlingapi.dto.UserDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.model.UserProfile;
import com.assigndevelopers.airportluggagehandlingapi.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
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

    public Passenger login(String id, String ticketNumber) {
        return passengerRepository.findByIdentificationAndTicketNumber(id, ticketNumber)
                .orElseThrow(
                        ()-> new RuntimeException("Invalid passenger ticket or passport or driver's license")
                );
    }
    public @NonNull AuthResultDTO getAuthResult(Passenger passenger) {

        PassengerDTO passengerDTO = new PassengerDTO(
                passenger.getFlightCode(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getIdentification(),
                passenger.getTicketNumber(),
                passenger.getStatus()
        );

        return AuthResultDTO.successPassenger(passengerDTO);
    }

    // This is 6 digits Identification number from Passport or Driver License
    public Optional<Passenger> findByTicketNumber(String flightNumber) {
        return passengerRepository.findByTicketNumber(flightNumber);
    }

    // This is 6 digits Identification number from Passport or Driver License
    public Optional<Passenger> findByIdNumber(String id) {
        return passengerRepository.findByIdentification(id);
    }

    public Optional<Passenger> findByIdAndTicket(String id, String ticketNumber) {
        return passengerRepository.findByIdentificationAndTicketNumber(id, ticketNumber);
    }

    public Passenger create(PassengerDTO dto) {
        Passenger passenger = new Passenger();

        passenger.setFirstName(dto.firstName());
        passenger.setLastName(dto.lastName());
        passenger.setFlightCode(dto.flightCode());
        passenger.setStatus(dto.status());
        passenger.setIdentification(dto.idNumber());
        passenger.setTicketNumber(dto.ticketNumber());

        return passengerRepository.save(passenger);
    }

    public Passenger changeFlight(String ticket, String newFlightCode) {
        Passenger passenger = passengerRepository.findByTicketNumber(ticket)
                .orElseThrow(
                        () -> new RuntimeException("Passenger with ticket number " + ticket + " not found")
                );
        // Return, if current flight same as new flight update
        if (passenger.getFlightCode().equals(newFlightCode)) {
            return passenger;
        }

        passenger.setFlightCode(newFlightCode);

        return passengerRepository.save(passenger);
    }

    public Passenger updateStatus(String ticket, String isBoarding) {
        Passenger passenger = passengerRepository.findByTicketNumber(ticket)
                .orElseThrow(
                        () -> new RuntimeException("Passenger with ticket number " + ticket + " not found")
                );

        PassengerStatus status =
                isBoarding.equals("yes") ?
                        PassengerStatus.BOARDED :
                        PassengerStatus.CHECKED_IN;

        passenger.setStatus(status.toString());

        return passengerRepository.save(passenger);
    }

    @Transactional
    public void delete(String id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Passenger with ID: " + id + " not found")
                );

        passengerRepository.delete(passenger);
    }

    @Transactional
    public void deleteByTicket(String ticket) {
        Passenger passenger = passengerRepository.findByTicketNumber(ticket)
                .orElseThrow(
                        () -> new RuntimeException("Passenger with ticket: " + ticket + " not found")
                );

        passengerRepository.delete(passenger);
    }
}
