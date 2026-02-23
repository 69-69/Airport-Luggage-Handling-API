package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Optional<Flight> findByFlightNumber(String flightNumber){
        return flightRepository.findByFlightNumber(flightNumber);
    }

    public Optional<Flight> getFlightByGate(String gate){
        return flightRepository.getFlightByGate(gate);
    }

    public Optional<List<Flight>> getAllFlights(){
        return Optional.of(flightRepository.findAll());
    }

    public void save(Flight flight){
        flightRepository.save(flight);
    }
}
