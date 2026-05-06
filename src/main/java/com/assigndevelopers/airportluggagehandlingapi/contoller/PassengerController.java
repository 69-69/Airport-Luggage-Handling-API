package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResultDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.PassengerLoginDTO;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResultDTO> login(@RequestBody PassengerLoginDTO dto) {
        String identification = dto.identification();
        String ticket = dto.ticket();

        Passenger passenger = passengerService.login(identification, ticket);
        AuthResultDTO authResultDTO = passengerService.getAuthResult(passenger);
        return ResponseEntity.ok(authResultDTO);

    }

    @PostMapping("/{identification}/logout")
    public ResponseEntity<?> logout(@PathVariable String identification) {

        passengerService.findByIdNumber(identification);
        return ResponseEntity.ok(Map.of("message", "Passenger successfully logout"));

    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PassengerDTO dto) {
        try {
            passengerService.findByIdNumber(dto.idNumber())
                    .ifPresent(
                            id -> {
                                throw new RuntimeException("Passenger with ID: " + dto.idNumber() + " already exists");
                            }
                    );
            passengerService.findByTicketNumber(dto.ticketNumber())
                    .ifPresent(
                            ticket -> {
                                throw new RuntimeException(dto.ticketNumber() + " number already assigned to " + ticket.getFirstName());
                            }
                    );

            Passenger passenger = passengerService.create(dto);

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

    @PostMapping("/ticket/id")
    public ResponseEntity<Passenger> getByTicketAndId(@RequestBody Map<String, String> body) {
        String idNumber = body.get("idNumber");
        String ticketNumber = body.get("ticketNumber");

        Passenger passenger = passengerService.findByIdAndTicket(idNumber, ticketNumber)
                .orElseThrow(
                        () -> new RuntimeException("Passenger not found")
                );

        return ResponseEntity.ok().body(passenger);
    }
    //

    @PutMapping("/change-flight/{ticket}")
    public ResponseEntity<?> changeFlight(
            @PathVariable String ticket,
            @RequestBody Map<String, String> body
    ) {
        String newFlightCode = body.get("flightCode");

        Passenger passenger = passengerService.changeFlight(ticket, newFlightCode);

        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/updateStatus/{isBoarding}")
    public ResponseEntity<?> updateStatus(@RequestBody Map<String, String> body, @PathVariable String isBoarding) {
        String ticket = body.get("ticketNumber");

        Passenger passenger = passengerService.updateStatus(ticket, isBoarding);

        return ResponseEntity.ok(passenger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        passengerService.delete(id);

        return ResponseEntity.ok(Map.of("message", "Passenger with bagId: " + id + " deleted"));
    }

    @DeleteMapping("/byTicket/{ticket}")
    public ResponseEntity<?> deleteByTicket(@PathVariable String ticket) {
        passengerService.deleteByTicket(ticket);

        return ResponseEntity.ok(Map.of("message", "Passenger with ticket: " + ticket + " deleted"));
    }

}
