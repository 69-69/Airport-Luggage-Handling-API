package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.service.PassengerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PassengerDTO dto) {
        try {
            Optional<Passenger> flightOpt = passengerService.findByIdNumber(dto.idNumber());
            if (flightOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Passenger with ID " + dto.idNumber() + " already exists");
            }

            Passenger passenger = passengerService.save(dto);

            // Return new Passenger Object with 201 CREATED Status
            return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred while adding the dto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> get() {
        Optional<List<Passenger>> passengers = passengerService.getAll();
        if (passengers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return passengers.map(passengerList -> ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerList)).orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping("/{ticketNumber}")
    public ResponseEntity<Passenger> getByTicket(@PathVariable String ticketNumber) {
        Optional<Passenger> passengerOpt = passengerService.findByTicketNumber(ticketNumber);
        if (passengerOpt.isPresent()) {
            return ResponseEntity.ok().body(passengerOpt.get());
        }
        throw new EntityNotFoundException("Passenger not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        passengerService.delete(id);

        return ResponseEntity.ok(Map.of("message", "Passenger with bagId: " + id + " deleted"));
    }

}
