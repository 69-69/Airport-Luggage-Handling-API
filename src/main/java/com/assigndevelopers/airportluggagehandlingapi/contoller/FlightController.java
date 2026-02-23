package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.FlightDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.repository.FlightRepository;
import com.assigndevelopers.airportluggagehandlingapi.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFlight(@RequestBody FlightDTO flight) {
        try {
            Optional<Flight> flightOpt = flightService.getFlightByGate(flight.getGate());
            if (flightOpt.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Flight with gate " + flight.getGate() + " already exists");
            }

            // Create a new flight from the provided flightDTO
            // String flightAbbrev = flight.getAirlineName().substring(0, 2).toUpperCase();
            // String flightNumber = flightAbbrev + String.format("%04d", flight.getFlightId());

            // Create a new flight from the provided flightDTO
            Flight newFlight = new Flight();

            newFlight.setAirlineName(flight.getAirlineName());
            newFlight.setFlightId(flight.getFlightId());
            newFlight.setFlightId(flight.getFlightId());
            newFlight.setFlightNumber(flight.getFlightNumber());
            newFlight.setDepartureTime(flight.getDepartureTime());
            newFlight.setDestination(flight.getDestination());
            newFlight.setTerminal(flight.getTerminal());
            newFlight.setGate(flight.getGate());

            flightService.save(newFlight);

            // Return new Flight Object with 201 CREATED Status
            return ResponseEntity.status(HttpStatus.CREATED).body(newFlight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred while adding the flight: " + e.getMessage());
        }
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlight(@PathVariable String flightNumber) {
        Optional<Flight> flightOpt = flightService.getFlightByGate(flightNumber);
        return flightOpt
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> flightOpt.map(ResponseEntity::ok).orElseGet(
                                () -> ResponseEntity.notFound().build()
                        )
                );
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        Optional<List<Flight>> flightOpt = flightService.getAllFlights();

        return flightOpt
                .map(ResponseEntity::ok)
                .orElseGet(
                        () -> ResponseEntity.notFound().build()
                );
    }

}
