package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.BagDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.BagResponse;
import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import com.assigndevelopers.airportluggagehandlingapi.service.BagService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/bags")
public class BagController {
    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody List<BagDTO> dtos) {
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

    @GetMapping
    public ResponseEntity<List<BagDTO>> getBags() {
        List<BagDTO> bags = bagService.findAll();

        if (bags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }

        return ResponseEntity.ok(bags);
    }

    @GetMapping("/{ticketNumber}")
    public ResponseEntity<?> getByTicket(@PathVariable String ticketNumber) {
        List<BagDTO> bags = bagService.findByTicket(ticketNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bags);
    }

    @PutMapping("/update-location/{bagId}/{location}")
    public ResponseEntity<Bag> updateBagLocation(@PathVariable String bagId, @PathVariable String location) {
        Bag bag = bagService.updateLocation(Long.valueOf(bagId), location);

        return ResponseEntity.status(HttpStatus.OK).body(bag);
    }
}