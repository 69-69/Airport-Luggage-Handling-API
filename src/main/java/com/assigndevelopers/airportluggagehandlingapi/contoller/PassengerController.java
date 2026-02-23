package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Passenger;
import com.assigndevelopers.airportluggagehandlingapi.service.PassengerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPassenger(@RequestBody PassengerDTO passenger) {
        passengerService.save(passenger);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{passenger_id}")
    public void deletePassenger(@PathVariable String passengerId) {
        Optional<Passenger> passengerOpt = passengerService.findById(Long.valueOf(passengerId));
        if (passengerOpt.isPresent()) {
            passengerService.delete(passengerOpt.get()); // This will cascade delete ticket and bags
        } else {
            throw new EntityNotFoundException("Passenger not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        Optional<List<Passenger>> passengers = passengerService.getAll();
        if (passengers.isEmpty() || !passengers.get().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return passengers.map(passengerList -> ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerList)).orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping("/{ticketNumber}")
    public ResponseEntity<Passenger> getPassenger(@PathVariable String ticketNumber) {
        Optional<Passenger> passengerOpt = passengerService.findByTicketNumber(ticketNumber);
        if (passengerOpt.isPresent()) {
            return ResponseEntity.ok().body(passengerOpt.get());
        }
        throw new EntityNotFoundException("Passenger not found");
    }
}
