package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.model.Message;
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

    public Optional<Message> findById(Long messageId) {
        return messageRepo.findById(messageId);
    }

    public Optional<List<Message>> findByFrom(String role) {
        return messageRepo.findByFrom(role);
    }

    public List<Message> getAll() {
        return messageRepo.findAll();
    }

    public void save(Message message) {
        messageRepo.save(message);
    }
}
