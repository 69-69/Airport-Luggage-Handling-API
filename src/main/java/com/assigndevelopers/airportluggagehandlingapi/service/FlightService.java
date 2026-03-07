package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.ChangeGateDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.FlightDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // FlightNumber: 123456
    public Optional<Flight> findByFlightCode(String flightNumber) {
        return flightRepository.findByFlightCode(flightNumber);
    }

    public Optional<Flight> getFlightByGate(String gate) {
        return flightRepository.findFlightByGate(gate);
    }

    public Optional<List<Flight>> getAllFlights() {
        return Optional.of(flightRepository.findAll());
    }

    public Flight create(FlightDTO dto) {
        // Create a new dto from the provided flightDTO
        // String flightAbbrev = dto.getAirlineName().substring(0, 2).toUpperCase();
        // String flightCode = flightAbbrev + String.format("%04d", dto.getFlightId());

        // Create a new dto from the provided flightDTO
        Flight flight = new Flight();

        flight.setAirlineName(dto.airlineName());
        flight.setFlightCode(dto.flightCode());
        flight.setDepartureTime(dto.departureTime());
        flight.setDestination(dto.destination());
        flight.setTerminal(dto.terminal());
        flight.setGate(dto.gate());
        flight.setIsBoarding(false);

        return flightRepository.save(flight);
    }

    @Transactional
    public void deleteByFlightCode(String code) {

        Flight flight = flightRepository.findByFlightCode(code)
                .orElseThrow(
                        () -> new RuntimeException("Flight with code: " + code + " not found")
                );

        flightRepository.delete(flight);
    }

    public void changeGate(String flightCode, ChangeGateDTO dto) {
        Flight flight = flightRepository.findByFlightCode(flightCode)
                .orElseThrow(
                        () -> new RuntimeException("Flight with ID: " + flightCode + " not found")
                );

        flight.setFlightCode(dto.gate());
        flight.setTerminal(dto.terminal());

        flightRepository.save(flight);
    }

    public List<Flight> findByAirlineCodeAndGate(String airlineCode, String gate) {

        List<Flight> flights = flightRepository
                .findByFlightCodeStartingWithAndGate(airlineCode, gate);

        if (flights.isEmpty()) {
            throw new RuntimeException(
                    "No flights found for airline code: " + airlineCode + " at gate: " + gate
            );
        }

        return flights;
    }
}
