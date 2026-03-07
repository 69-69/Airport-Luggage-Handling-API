package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MsgResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.MessageBoard;
import com.assigndevelopers.airportluggagehandlingapi.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageDTO dto) {
        try {
            MessageBoard message = messageService.saveMessage(dto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(message);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send message " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MsgResponseDTO>> getAll() {
        List<MsgResponseDTO> messages = messageService.getAll();

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> markAsRead(@PathVariable String id) {
        MessageBoard msg = messageService.markAsRead(Long.valueOf(id));

        return ResponseEntity.ok(msg);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        messageService.deleteById(id);

        return ResponseEntity.ok(Map.of("message", "Message with ID: " + id + " deleted"));
    }

    @GetMapping("/sent/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getMessagesSentByRole(@PathVariable String role) {

        List<MsgResponseDTO> messages = messageService.findBySenderRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/received/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getMessagesReceivedByRole(
            @PathVariable String role) {
        List<MsgResponseDTO> messages = messageService.findByRecipientRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }
}
