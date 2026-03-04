package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.model.Bag;
import com.assigndevelopers.airportluggagehandlingapi.repository.BagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BagService {
    private  final BagRepository bagRepository;

    public BagService(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public Optional<List<Bag>> findAll() {
        return Optional.of(bagRepository.findAll());
    }

    public Optional<Bag> findById(Long id) {
        return bagRepository.findById(id);
    }

    public Optional<Bag> findByTicketNumber(String ticketNumber) {
        return bagRepository.findByPassengerTicketNumber(ticketNumber);
    }
}
