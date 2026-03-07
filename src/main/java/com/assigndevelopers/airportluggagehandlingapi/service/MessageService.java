package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.MessageDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.MsgResponseDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.MessageBoard;
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

    public @NonNull MessageBoard saveMessage(MessageDTO dto) {
        User sender = userRepository.findByUsername(dto.from())
                .orElseThrow(
                        () -> new RuntimeException("Sender Id does not exist")
                );
        User recipient = userRepository.findByUsername(dto.to())
                .orElseThrow(
                        () -> new RuntimeException("Recipient Id does not exist")
                );

        MessageBoard message = new MessageBoard();

        message.setMessage(dto.message());
        message.setAirline(dto.airline());
        message.setRecipient(recipient);
        message.setSender(sender);
        message.setRead(false);

        return messageRepo.save(message);
    }

    public Optional<MessageBoard> findById(Long messageId) {
        return messageRepo.findById(messageId);
    }

    public List<MsgResponseDTO> findBySenderRole(String role) {
        return messageRepo.findBySenderRole(role)
                .stream()
                .map(MessageService::getMsgResponseDTO)
                .collect(Collectors.toList());
    }

    public List<MsgResponseDTO> findByRecipientRole(String role) {
        return messageRepo.findByRecipientRole(role)
                .stream()                       // Stream<MessageBoard>
                .map(MessageService::getMsgResponseDTO)  // Mapper expects MessageBoard
                .collect(Collectors.toList());  // List<MsgResponseDTO>
    }

    public List<MsgResponseDTO> getAll() {
        return messageRepo.findAll()
                .stream()
                .map(MessageService::getMsgResponseDTO)
                .collect(Collectors.toList());
    }

    private static @NonNull MsgResponseDTO getMsgResponseDTO(MessageBoard msg) {
        return new MsgResponseDTO(
                msg.getMessage(),
                msg.getRecipient().getUsername(),
                msg.getSender().getUsername(),
                msg.getAirline(),
                msg.getRead(),
                msg.getCreatedAt()
        );
    }

    public void save(MessageBoard message) {
        messageRepo.save(message);
    }

    public void deleteById(String id) {
        MessageBoard msg = messageRepo.findById(Long.valueOf(id))
                .orElseThrow(
                        () -> new RuntimeException("Message with ID: " + id + " not found")
                );

        messageRepo.delete(msg);
    }

    public MessageBoard markAsRead(Long id) {
        MessageBoard msg = messageRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Message isRead not updated")
        );
        msg.setRead(true);
        messageRepo.save(msg);

        return msg;
    }
}
