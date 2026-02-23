package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Message;
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

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDTO message) {
        try {
            Message newMessage = new Message();

            newMessage.setMessage(message.getMessage());
            newMessage.setAirline(message.getAirline());
            newMessage.setTo(message.getTo());
            newMessage.setFrom(message.getFrom());
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
    public ResponseEntity<List<Message>> getAll() {
        List<Message> messages = messageService.getAll();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/inbox/{from_role}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable("from_role") String role) {
        Optional<List<Message>> messageOpt = messageService.findByFrom(role);

        return messageOpt.map(
                messages -> ResponseEntity.status(HttpStatus.FOUND).body(messages)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of())
        );
    }
}
