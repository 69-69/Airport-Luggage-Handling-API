package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResultDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.LoginDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.RegisterDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.UserDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.model.UserProfile;
import com.assigndevelopers.airportluggagehandlingapi.service.UserService;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        Optional<User> userOpt = userService.findByEmail(registerDTO.email());

        if (userOpt.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }

        User user = setUser(registerDTO);
        userService.save(user);

        // Return new User with a 201 CREATED Status
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private static @NonNull User setUser(RegisterDTO registerDTO) {
        User user = new User();
        UserProfile profile = new UserProfile();

        user.setRole(registerDTO.role());
        user.setUsername(registerDTO.username());
        user.setPassword(registerDTO.password());
        user.setFirstLogin(true);

        profile.setEmail(registerDTO.email());
        profile.setFirstname(registerDTO.firstname());
        profile.setLastname(registerDTO.lastname());
        profile.setAirline(registerDTO.airline());
        profile.setPhone(registerDTO.phone());

        user.setProfile(profile);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResultDTO> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> userOpt = userService.findByUsername(loginDTO.username());

        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), loginDTO.password())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(AuthResultDTO.failure("Invalid username or password"));
        }

        AuthResultDTO authResultDTO = getAuthResult(userOpt.get());

        return ResponseEntity.ok(authResultDTO);
    }

    private static @NonNull AuthResultDTO getAuthResult(User user) {
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

        return AuthResultDTO.success(userDTO);
    }
}
