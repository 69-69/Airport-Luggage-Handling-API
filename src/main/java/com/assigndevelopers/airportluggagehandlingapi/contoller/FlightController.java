package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.FlightDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.repository.FlightRepository;
import com.assigndevelopers.airportluggagehandlingapi.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody FlightDTO dto) {
        try {
            Optional<Flight> flightOpt = flightService.getFlightByGate(dto.gate());
            if (flightOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Flight with gate " + dto.gate() + " already exists");
            }

            Flight flight = flightService.save(dto);

            // Return new Flight Object with 201 CREATED Status
            return ResponseEntity.status(HttpStatus.CREATED).body(flight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred while adding the dto: " + e.getMessage());
        }
    }

    @GetMapping("/{flightCode}")
    public ResponseEntity<Flight> getByCode(@PathVariable String flightCode) {
        Optional<Flight> flightOpt = flightService.findByFlightCode(flightCode);

        return flightOpt.map(ResponseEntity::ok).orElseGet(() -> flightOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping
    public ResponseEntity<List<Flight>> get() {
        Optional<List<Flight>> flightOpt = flightService.getAllFlights();

        return flightOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{flightCode}")
    public ResponseEntity<?> delete(@PathVariable String flightCode) {

        flightService.deleteByFlightCode(flightCode);

        return ResponseEntity.ok(Map.of("message", "Flight with code: " + flightCode + " deleted"));
    }
}
