package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResult;
import com.assigndevelopers.airportluggagehandlingapi.dto.LoginRequest;
import com.assigndevelopers.airportluggagehandlingapi.dto.RegisterRequest;
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
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        Optional<User> userOpt = userService.findByEmail(registerRequest.getFirstname());

        if (userOpt.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("User with this email already exists");
        }

        User user = setUser(registerRequest);
        userService.save(user);

        // Return new User with a 201 CREATED Status
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    private static @NonNull User setUser(RegisterRequest registerRequest) {
        User user = new User();
        UserProfile profile =  new UserProfile();

        user.setRole(registerRequest.getRole());
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setFirstLogin(true);

        profile.setEmail(registerRequest.getEmail());
        profile.setFirstname(registerRequest.getFirstname());
        profile.setLastname(registerRequest.getLastname());
        profile.setAirline(registerRequest.getAirline());
        profile.setPhone(registerRequest.getPhone());

        user.setProfile(profile);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResult> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userService.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), loginRequest.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResult("Invalid username or password"));
        }

        AuthResult authResult = getAuthResult(userOpt.get());

        return ResponseEntity.ok(authResult);
    }

    private static @NonNull AuthResult getAuthResult(User user) {
        UserProfile profile = user.getProfile();

        UserDTO userDTO = new UserDTO(
                user.getRole(),
                user.getUsername(),
                user.isFirstLogin(),

                profile.getEmail(),
                profile.getPhone(),
                profile.getFirstname(),
                profile.getLastname(),
                profile.getAirline()
        );

        return new AuthResult(userDTO);
    }
}
