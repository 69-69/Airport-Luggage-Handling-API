package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MessageResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.ModifyMessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MsgResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Message;
import com.assigndevelopers.airportluggagehandlingapi.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@Valid @RequestBody MessageDTO dto) {
        try {
            MessageResponseDTO message = messageService.create(dto);

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
    public ResponseEntity<List<MsgResponseDTO>> get() {
        List<MsgResponseDTO> messages = messageService.getAll();

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @PutMapping("/read-status")
    public ResponseEntity<?> markAsRead(@RequestBody Map<String, String> body) {
        String id = body.get("id");
        boolean isRead = Boolean.parseBoolean(body.get("isRead"));
        Message msg = messageService.markAsRead(id, isRead);

        return ResponseEntity.ok(msg);
    }

    @PostMapping("/deleteByMessage")
    public ResponseEntity<?> deleteByMessage(@RequestBody ModifyMessageDTO request) {

        messageService.deleteById(request.message());

        return ResponseEntity.ok(Map.of("message", "Message deleted"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        messageService.deleteById(id);

        return ResponseEntity.ok(Map.of("message", "Message with ID: " + id + " deleted"));
    }

    @GetMapping("/sent/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getBySentRole(@PathVariable String role) {

        List<MsgResponseDTO> messages = messageService.findBySenderRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }

    @GetMapping("/received/role/{role}")
    public ResponseEntity<List<MsgResponseDTO>> getByReceivedRole(
            @PathVariable String role) {
        List<MsgResponseDTO> messages = messageService.findByRecipientRole(role);

        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messages);
        }

        return ResponseEntity.ok(messages);
    }
}
