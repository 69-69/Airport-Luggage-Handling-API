package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MessageResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MsgResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.Message;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.repository.MessageRepository;
import com.assigndevelopers.airportluggagehandlingapi.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepo;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepo, UserRepository userRepository) {
        this.messageRepo = messageRepo;
        this.userRepository = userRepository;
    }

    public @NonNull MessageResponseDTO create(MessageDTO dto) {
        // Sender can be any Admin or Staff member (ADMIN, AIRLINE, GATE or GROUND STAFFS)
        User sender = userRepository.findByUsername(dto.fromUsername())
                .orElseThrow(
                        () -> new RuntimeException("Sender Id does not exist")
                );
        /* // ADMIN is always the recipient
        User recipient = userRepository.findByRole(dto.to())
                .orElseThrow(
                        () -> new RuntimeException("Recipient Id does not exist")
                );*/

        Message newMessage = new Message();

        newMessage.setMessage(dto.message());
        newMessage.setAirline(dto.airline());
        newMessage.setRecipient(dto.to());
        newMessage.setSender(sender);
        newMessage.setRead(false);

        Message message = messageRepo.save(newMessage);
        return new MessageResponseDTO(
                message.getId(),
                message.getMessage(),
                message.getAirline(),
                message.getRecipient(),
                message.getSender().getUsername(),
                message.getRead()
        );
    }

    public Optional<Message> findById(Long messageId) {
        return messageRepo.findById(messageId);
    }

    public List<MsgResponseDTO> findBySenderRole(String role) {
        return messageRepo.findBySenderRole(role)
                .stream()
                .map(MessageService::getMsgResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MsgResponseDTO> findByRecipientRole(String role) {
        return messageRepo.findByRecipient(role)
                .stream()                       // Stream<Message>
                .map(MessageService::getMsgResponseDTO)  // Mapper expects MessageBoard
                .collect(Collectors.toList());  // List<MsgResponseDTO>
    }

    public List<MsgResponseDTO> getAll() {
        return messageRepo.findAll()
                .stream()
                .map(MessageService::getMsgResponseDTO)
                .collect(Collectors.toList());
    }

    private static @NonNull MsgResponseDTO getMsgResponseDTO(Message msg) {
        return new MsgResponseDTO(
                msg.getId(),
                msg.getMessage(),
                msg.getRecipient(),
                msg.getSender().getRole(),
                msg.getSender().getUsername(),
                msg.getAirline(),
                msg.getRead(),
                msg.getCreatedAt()
        );
    }

//    public void save(Message message) {
//        messageRepo.save(message);
//    }

    public void deleteById(String id) {
        Message msg = messageRepo.findById(Long.valueOf(id))
                .orElseThrow(
                        () -> new RuntimeException("Message with ID: " + id + " not found")
                );

        messageRepo.delete(msg);
    }

    public void deleteByMessage(String message) {

        Message msg = messageRepo.findByMessage(message)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        messageRepo.delete(msg);
    }

    public Message markAsRead(String  id, boolean isRead) {
        Message msg = messageRepo.findById(Long.valueOf(id)).orElseThrow(
                () -> new RuntimeException("Message isRead not updated")
        );

        msg.setRead(isRead);
        messageRepo.save(msg);

        return msg;
    }
}
