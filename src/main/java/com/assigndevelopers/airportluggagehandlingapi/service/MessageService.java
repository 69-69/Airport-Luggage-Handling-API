package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.model.MessageBoard;
import com.assigndevelopers.airportluggagehandlingapi.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepo;

    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Optional<MessageBoard> findById(Long messageId) {
        return messageRepo.findById(messageId);
    }

    public Optional<List<MessageBoard>> findBySenderRole(String role) {
        return messageRepo.findBySenderRole(role);
    }

    public Optional<List<MessageBoard>> findByRecipientRole(String role) {
        return messageRepo.findByRecipientRole(role);
    }

    public List<MessageBoard> getAll() {
        return messageRepo.findAll();
    }

    public void save(MessageBoard message) {
        messageRepo.save(message);
    }
}
