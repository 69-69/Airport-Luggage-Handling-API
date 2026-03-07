package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResultDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.RegisterDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.UserDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.model.UserProfile;
import com.assigndevelopers.airportluggagehandlingapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByProfileEmail(email);
    }

    public Optional<User> findByPhone(String email) {
        return userRepository.findByProfilePhone(email);
    }

    public @NonNull User create(RegisterDTO dto) {
        userRepository.findByProfileEmail(dto.email())
                .ifPresent(user -> {
                    throw new RuntimeException("User with this email: " + dto.email() + " already exists");
                });

        userRepository.findByProfilePhone(dto.phone())
                .ifPresent(user -> {
                    throw new RuntimeException("User with this phone: " + dto.phone() + " already exists");
                });

        // If we reach here, email and phone are unique → create user
        User user = new User();
        UserProfile profile = new UserProfile();

        user.setRole(dto.role());
        user.setUsername(dto.username());
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(hashedPassword);
        user.setFirstLogin(true);

        profile.setEmail(dto.email());
        profile.setFirstname(dto.firstName());
        profile.setLastname(dto.lastName());
        profile.setAirline(dto.airline());
        profile.setPhone(dto.phone());

        user.setProfile(profile);
        userRepository.save(user);

        return user;
    }

    public @NonNull AuthResultDTO getAuthResult(User user, boolean isAuth) {
        UserProfile profile = user.getProfile();

        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                profile.getEmail(),
                profile.getPhone(),
                user.getRole(),
                profile.getFirstname(),
                profile.getLastname(),
                profile.getAirline(),
                user.isFirstLogin()
        );

        return isAuth ? AuthResultDTO.success(userDTO) : AuthResultDTO.allUsers(userDTO);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByProfileEmailAndPassword(email, password);
    }

    public boolean checkPassword(User user, String password) {
//        return user.getPassword().equals(password);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public Optional<List<AuthResultDTO>> getUsers() {
        List<User> users = userRepository.findAll();

        var all = users
                .stream()
                .map(
                        user -> getAuthResult(user, false)
                )
                .collect(Collectors.toList());

        return Optional.of(all);

    }

    public void updatePassword(String username, String newPassword, boolean firstLogin) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("User with ID: " + username + " not found")
                );

        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        user.setFirstLogin(firstLogin);

        userRepository.save(user);
    }

    @Transactional
    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new RuntimeException("User with ID: " + username + " not found")
                );

        userRepository.delete(user);
    }

    @Transactional
    public void deleteByPhone(String phone) {
        User user = userRepository.findByProfilePhone(phone)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );

        userRepository.delete(user);
    }
}
