/// NOTE: This MainController was made modular into individua; controllers because I wanted track achuators and error
/*
package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.*;
import com.assigndevelopers.airportluggagehandlingapi.model.*;
import com.assigndevelopers.airportluggagehandlingapi.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {
    /// User Controller (/users)
    ///
    private final UserService userService;
    private final FlightService flightService;
    private final PassengerService passengerService;
    private final BagService bagService;
    private final MessageService messageService;

    public MainController(UserService userService, FlightService flightService, PassengerService passengerService, BagService bagService, MessageService messageService) {

        this.userService = userService;
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.bagService = bagService;
        this.messageService = messageService;
    }


    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO dto) {

        User user = userService.create(dto);

        // Return new User with a 201 CREATED Status
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<AuthResultDTO> login(@RequestBody LoginDTO dto) {

        User user = userService.login(dto);
        AuthResultDTO authResultDTO = userService.getAuthResult(user, true);
        return ResponseEntity.ok(authResultDTO);


        */
/*Optional<User> userOpt = userService.findByUsername(dto);

        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), dto.password())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResultDTO.failure("Invalid username or password"));
        }

        userService.setLoginStatus(userOpt.get());
        AuthResultDTO authResultDTO = userService.getAuthResult(userOpt, true);*//*

    }

    @PostMapping("/users/{username}/logout")
    public ResponseEntity<?> logout(@PathVariable String username) {
        userService.logout(username);
        return ResponseEntity.ok(Map.of("message", "User successfully logout"));

    }

    @GetMapping("/users")
    public ResponseEntity<List<AuthResultDTO>> getUsers() {
        var users = userService.getUsers();

        return users.map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new RuntimeException("No users exist")
                );
    }

    @PutMapping("/users/{username}/password")
    public ResponseEntity<?> updatePassword(@PathVariable String username, @RequestBody UpdatePasswordDTO dto) {
        userService.updatePassword(username, dto.newPassword(), dto.firstLogin());

        return ResponseEntity.ok(Map.of("message", "User password successfully updated"));
    }

    @DeleteMapping("/users/{phone}")
    public ResponseEntity<?> deleteUser(@PathVariable String phone) {
        userService.deleteByPhone(phone);

        return ResponseEntity.ok(Map.of("message", "User successfully deleted"));
    }


    /// Flight Controller (/flights)
    ///
    @PostMapping("/flights/add")
    public ResponseEntity<?> addFlight(@Valid @RequestBody FlightDTO dto) {
        try {
            flightService.getFlightByGate(dto.gate())
                    .ifPresent(
                            flight -> {
                                throw new RuntimeException("Flight with gate " + dto.gate() + " already exists");
                            }
                    );
            */
/*if (flightOpt.isPresent()) {
                return ResponseEntity.isRead(HttpStatus.CONFLICT).body();
            }*//*


            Flight flight = flightService.create(dto);

            // Return new Flight Object with 201 CREATED Status
            return ResponseEntity.status(HttpStatus.CREATED).body(flight);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred while adding the dto: " + e.getMessage());
        }
    }

    @GetMapping("/flights/{flightCode}")
    public ResponseEntity<Flight> getFlightByCode(@PathVariable String flightCode) {
        Optional<Flight> flightOpt = flightService.findByFlightCode(flightCode);

        return flightOpt.map(ResponseEntity::ok).orElseGet(() -> flightOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping("/flights/search")
    public ResponseEntity<?> getByAirlineCodeAndGate(
            @RequestParam String airlineCode,
            @RequestParam String gate
    ) {
        List<Flight> flights = flightService.findByAirlineCodeAndGate(airlineCode, gate);
        */
/*if (flights.isEmpty()) {
            throw new RuntimeException("Flight with ID: " + airlineCode + " not found");
        }*//*


        return ResponseEntity.ok(flights);
    }

    @PutMapping("/flights/change-gate/{flightCode}")
    public ResponseEntity<?> changeGate(
            @PathVariable String flightCode,
            @RequestBody ChangeGateDTO dto
    ) {
        flightService.changeGate(flightCode, dto);

        return ResponseEntity.ok(Map.of("message", "Flight gate successfully changed"));
    }

    @PutMapping("/flights/update-status/{flightCode}/{status}")
    public ResponseEntity<?> updateFlightStatus(
            @PathVariable String flightCode,
            @PathVariable String status
    ) {
        flightService.updateStatus(flightCode, status);

        return ResponseEntity.ok(Map.of("message", "Flight status successfully updated"));
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getFlight() {
        Optional<List<Flight>> flightOpt = flightService.getAllFlights();

        return flightOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/flights/{flightCode}")
    public ResponseEntity<?> deleteFlight(@PathVariable String flightCode) {

        flightService.deleteByFlightCode(flightCode);

        return ResponseEntity.ok(Map.of("message", "Flight with code: " + flightCode + " deleted"));
    }

    /// Passenger Controller (/passengers)
    ///
    @PostMapping("/passengers/login")
    public ResponseEntity<AuthResultDTO> loginPassenger(@RequestBody PassengerLoginDTO dto) {
        String identification = dto.identification();
        String ticket = dto.ticket();

        Passenger passenger = passengerService.login(identification, ticket);
        AuthResultDTO authResultDTO = passengerService.getAuthResult(passenger);
        return ResponseEntity.ok(authResultDTO);

    }

    @PostMapping("/passengers/{identification}/logout")
    public ResponseEntity<?> logoutPassenger(@PathVariable String identification) {

        passengerService.findByIdNumber(identification);
        return ResponseEntity.ok(Map.of("message", "Passenger successfully logout"));

    }
    @PostMapping("/passengers/add")
    public ResponseEntity<?> addPassenger(@Valid @RequestBody PassengerDTO dto) {
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
            */
/*Optional<Passenger> flightOpt = passengerService.findByIdNumber(dto.idNumber());
            if (flightOpt.isPresent()) {
                return ResponseEntity.isRead(HttpStatus.CONFLICT).body();
            }*//*


            Passenger passenger = passengerService.create(dto);

            // Return new Passenger Object with 201 CREATED Status
            return ResponseEntity.status(HttpStatus.CREATED).body(passenger);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("An error occurred while adding the dto: " + e.getMessage());
        }
    }

    @GetMapping("/passengers")
    public ResponseEntity<List<Passenger>> getPassengers() {
        Optional<List<Passenger>> passengers = passengerService.getAll();
        if (passengers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return passengers.map(passengerList -> ResponseEntity
                .status(HttpStatus.OK)
                .body(passengerList)).orElseGet(() -> ResponseEntity.ok().build());
    }

    @GetMapping("/passengers/{ticketNumber}")
    public ResponseEntity<Passenger> getPassengerByTicket(@PathVariable String ticketNumber) {
        Optional<Passenger> passengerOpt = passengerService.findByTicketNumber(ticketNumber);
        if (passengerOpt.isPresent()) {
            return ResponseEntity.ok().body(passengerOpt.get());
        }
        throw new EntityNotFoundException("Passenger not found");
    }

    @PostMapping("/passengers/ticket/id")
    public ResponseEntity<Passenger> getPassengerByTicketAndId(@RequestBody Map<String, String> body) {
        String idNumber = body.get("idNumber");
        String ticketNumber = body.get("ticketNumber");

        Passenger passenger = passengerService.findByIdAndTicket(idNumber, ticketNumber)
                .orElseThrow(
                        () -> new RuntimeException("Passenger not found")
                );

        return ResponseEntity.ok().body(passenger);
    }


    @PutMapping("/passengers/change-flight/{ticket}")
    public ResponseEntity<?> changePassengerFlight(
            @PathVariable String ticket,
            @RequestBody Map<String, String> body
    ) {
        String newFlightCode = body.get("flightCode");

        Passenger passenger = passengerService.changeFlight(ticket, newFlightCode);

        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/passengers/updateStatus/{isBoarding}")
    public ResponseEntity<?> updatePassengerStatus(@RequestBody Map<String, String> body, @PathVariable String isBoarding) {
        String ticket = body.get("ticketNumber");

        Passenger passenger = passengerService.updateStatus(ticket, isBoarding);

        return ResponseEntity.ok(passenger);
    }

    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable String id) {
        passengerService.delete(id);

        return ResponseEntity.ok(Map.of("message", "Passenger with bagId: " + id + " deleted"));
    }

    @DeleteMapping("/passengers/byTicket/{ticket}")
    public ResponseEntity<?> deletePassengerByTicket(@PathVariable String ticket) {
        passengerService.deleteByTicket(ticket);

        return ResponseEntity.ok(Map.of("message", "Passenger with ticket: " + ticket + " deleted"));
    }


    /// Bag Controller (/bags)
    ///
    @PostMapping("/bags/add")
    public ResponseEntity<?> addBag(@Valid @RequestBody List<BagDTO> dtos) {
        try {
            List<BagResponse> bags = dtos
                    .stream()
                    .map(bagService::saveBag)
                    .toList();

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(bags);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add bag " + e.getMessage());
        }
    }

    @GetMapping("/bags")
    public ResponseEntity<List<BagDTO>> getBags() {
        List<BagDTO> bags = bagService.findAll();

        if (bags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.ok(bags);
    }

    @GetMapping("/bags/{ticketNumber}")
    public ResponseEntity<?> getBagsByTicket(@PathVariable String ticketNumber) {
        List<BagDTO> bags = bagService.findByTicket(ticketNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bags);
    }

    @DeleteMapping("/bags/{bagId}")
    public ResponseEntity<?> deleteBag(@PathVariable String bagId) {

        bagService.deleteById(bagId);

        return ResponseEntity.ok(Map.of("message", "Bag with ID: " + bagId + " deleted"));
    }

    @PatchMapping("/bags/update-location/{bagId}/{newLocation}")
    public ResponseEntity<Bag> updateBagLocation(@PathVariable String bagId, @PathVariable String newLocation) {
        Bag bag = bagService.updateLocation(Long.valueOf(bagId), newLocation);

        return ResponseEntity.status(HttpStatus.OK).body(bag);
    }


    /// Message Controller (/messages)
    ///
    @PostMapping("/messages/send")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDTO dto) {
        try {
            Message message = messageService.create(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message " + e.getMessage());
        }
    }

    @GetMapping("messages")
    public ResponseEntity<List<MsgResponseDTO>> getMessages() {
        List<MsgResponseDTO> messages = messageService.getAll();

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @PutMapping("/messages/read-status")
    public ResponseEntity<?> markAsRead(@RequestBody Map<String, String> body) {
        String id = body.get("id");
        boolean isRead = Boolean.parseBoolean(body.get("isRead"));
        Message msg = messageService.markAsRead(id, isRead);

        return ResponseEntity.ok(msg);
    }

    @PostMapping("/messages/deleteByMessage")
    public ResponseEntity<?> deleteByMessage(@RequestBody ModifyMessageDTO request) {

        messageService.deleteById(request.message());

        return ResponseEntity.ok(Map.of("message", "Message deleted"));
    }


    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable String id) {
        messageService.deleteById(id);

        return ResponseEntity.ok(Map.of("message", "Message with ID: " + id + " deleted"));
    }

    @GetMapping("/messages/sent/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getMessagesSentByRole(@PathVariable String role) {

        List<MsgResponseDTO> messages = messageService.findBySenderRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/received/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getMessagesReceivedByRole(
            @PathVariable String role) {
        List<MsgResponseDTO> messages = messageService.findByRecipientRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

}
*/
