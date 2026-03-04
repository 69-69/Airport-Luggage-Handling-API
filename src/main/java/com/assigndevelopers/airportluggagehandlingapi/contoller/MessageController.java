package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.MessageBoard;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.repository.UserRepository;
import com.assigndevelopers.airportluggagehandlingapi.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    public MessageController(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO message) {
        try {
            MessageBoard newMessage = new MessageBoard();

            User sender = userRepository.findByUsername(message.getFrom()).get();
            User recipient = userRepository.findByUsername(message.getTo()).get();

            newMessage.setMessage(message.getMessage());
            newMessage.setAirline(message.getAirline());
            newMessage.setRecipient(recipient);
            newMessage.setSender(sender);
            newMessage.setRead(false);

            messageService.save(newMessage);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(newMessage);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save message " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MessageBoard>> getAll() {
        List<MessageBoard> messages = messageService.getAll();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/sent/role/{role}")
    public ResponseEntity<List<MessageBoard>> getMessagesSentByRole(@PathVariable String role) {
        Optional<List<MessageBoard>> messageOpt = messageService.findBySenderRole(role);

        return messageOpt.map(
                messages -> ResponseEntity.status(HttpStatus.FOUND).body(messages)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of())
        );
    }

    @GetMapping("/messages/received/role/{role}")
    public ResponseEntity<List<MessageBoard>> getMessagesReceivedByRole(
            @PathVariable String role) {
        Optional<List<MessageBoard>> messageOpt = messageService.findByRecipientRole(role);

        return messageOpt.map(
                messages -> ResponseEntity.status(HttpStatus.FOUND).body(messages)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of())
        );
    }
}
