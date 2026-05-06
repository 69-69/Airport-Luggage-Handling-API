package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.BagDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.BagLocation;
import com.assigndevelopers.airportluggagehandlingapi.dto.BagResponse;
import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.repository.BagRepository;
import com.assigndevelopers.airportluggagehandlingapi.repository.FlightRepository;
import com.assigndevelopers.airportluggagehandlingapi.repository.PassengerRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BagService {
    private final BagRepository bagRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public BagService(BagRepository bagRepository, PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.bagRepository = bagRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public List<BagDTO> findAll() {

        return bagRepository.findAll()
                .stream()
                .map(BagService::toBagDTO)
                .collect(Collectors.toList());
    }

    public Optional<Bag> findById(Long id) {
        return bagRepository.findById(id);
    }

    public Optional<List<Bag>> findByPassengerID(String ticketNumber) {

        return bagRepository.findByPassengerIdentification(ticketNumber);
    }

    public List<BagDTO> findByTicket(String ticketNumber) {
        Passenger passenger = passengerRepository.findByTicketNumber(ticketNumber)
                .orElseThrow(
                        () -> new RuntimeException("Passenger not found with ticket number: " + ticketNumber)
                );

        return bagRepository.findByPassenger(passenger)
                .stream()
                .map(
                        BagService::toBagDTO
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(String id) {

        Bag bag = bagRepository.findById(Long.valueOf(id))
                .orElseThrow(
                        () -> new RuntimeException("Bag with ID: " + id + " not found")
                );

        bagRepository.delete(bag);
    }

    @Transactional
    public void deleteByTicket(String ticket) {

        List<Bag> bags = bagRepository.findByPassenger_TicketNumber(ticket);
        if (bags.isEmpty()){
            throw new RuntimeException("Bag associated with ticket: " + ticket + " not found");
        }

        bagRepository.delete(bags.getFirst());
    }

    public Bag updateLocation(Long id, String newLocation) {
        Bag bag = bagRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Bag with ID: " + id + " not found")
                );
        String newLoc = newLocation.isEmpty() ? BagLocation.LOADED.toString() : newLocation;
        bag.setLocation(newLoc);
        bagRepository.save(bag);

        return bag;
    }


    public BagResponse saveBag(@Valid BagDTO dto) {

        Passenger passenger = passengerRepository.findByTicketNumber(dto.ticketNumber())
                .orElseThrow(() ->
                        new RuntimeException("Passenger not found with ticket number: " + dto.ticketNumber())
                );

        Flight flight = flightRepository.findByFlightCode(passenger.getFlightCode())
                .orElseThrow(() ->
                        new RuntimeException("Flight not found with flight code: " + passenger.getFlightCode())
                );

        Bag bag = new Bag();
        bag.setFlight(flight);
        bag.setPassenger(passenger);
        bag.setId(dto.bagId());
        bag.setLocation(dto.location());
        bag.setWeight(dto.weight());

        Bag savedBag = bagRepository.save(bag);

        return getBagResponse(savedBag);
    }

    private static @NonNull BagResponse getBagResponse(Bag savedBag) {
        return new BagResponse(
                savedBag.getId(),
                savedBag.getLocation(),
                savedBag.getPassenger().getTicketNumber(),
                savedBag.getFlight(),
                savedBag.getCreatedAt()
        );
    }

    private static @NonNull BagDTO toBagDTO(Bag savedBag) {
        return new BagDTO(
                savedBag.getId(),
                savedBag.getLocation(),
                savedBag.getPassenger().getTicketNumber(),
                savedBag.getWeight()
//                savedBag.getFlight().getFlightCode(),
        );
    }
}
