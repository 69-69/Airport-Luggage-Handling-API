package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.ChangeGateDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.FlightDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Flight;
import com.assigndevelopers.airportluggagehandlingapi.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
            flightService.getFlightByGate(dto.gate())
                    .ifPresent(
                            flight -> {
                                throw new RuntimeException("Flight with gate " + dto.gate() + " already exists");
                            }
                    );
            /*if (flightOpt.isPresent()) {
                return ResponseEntity.isRead(HttpStatus.CONFLICT).body();
            }*/

            Flight flight = flightService.create(dto);

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

    @GetMapping("/search")
    public ResponseEntity<?> getByAirlineCodeAndGate(
            @RequestParam String airlineCode,
            @RequestParam String gate
    ) {
        List<Flight> flights = flightService.findByAirlineCodeAndGate(airlineCode, gate);
        if (flights.isEmpty()) {
            throw new RuntimeException("Flight with ID: " + airlineCode + " not found");
        }

        return ResponseEntity.ok(flights);
    }

    @PutMapping("/change-gate/{flightCode}")
    public ResponseEntity<?> changeGate(
            @PathVariable String flightCode,
            @RequestBody ChangeGateDTO dto
    ) {
        flightService.changeGate(flightCode, dto);

        return ResponseEntity.ok(Map.of("message", "Flight gate successfully changed"));
    }

    @PutMapping("/update-status/{flightCode}/{status}")
    public ResponseEntity<?> updateStatus(
            @PathVariable String flightCode,
            @PathVariable String status
    ) {
        flightService.updateStatus(flightCode, status);

        return ResponseEntity.ok(Map.of("message", "Flight status successfully updated"));
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
