package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import com.assigndevelopers.airportluggagehandlingapi.service.BagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bags")
public class BagController {
    private final BagService bagService;

    public BagController(BagService bagService) {
        this.bagService = bagService;
    }


    @GetMapping
    public ResponseEntity<List<Bag>> getBags() {
        Optional<List<Bag>> bags = bagService.findAll();

        return bags.map(bag -> ResponseEntity.status(HttpStatus.OK).body(bag)
        ).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<Bag>()));
    }

    @PutMapping("/update-location/{bagId}")
    public ResponseEntity<Bag> updateBagLocation(@PathVariable String bagId) {
        Optional<Bag> bagOpt = bagService.findById(Long.valueOf(bagId));
        if (bagOpt.isPresent()) {
            Bag bag = bagOpt.get();
            return ResponseEntity.status(HttpStatus.OK).body(bag);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Bag());
    }
}