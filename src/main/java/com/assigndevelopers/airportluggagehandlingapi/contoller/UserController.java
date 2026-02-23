package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResult;
import com.assigndevelopers.airportluggagehandlingapi.dto.LoginRequest;
import com.assigndevelopers.airportluggagehandlingapi.dto.RegisterRequest;
import com.assigndevelopers.airportluggagehandlingapi.dto.UserDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.service.UserService;
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

        User newUser = new User();

        newUser.setEmail(registerRequest.getEmail());
        newUser.setFirstname(registerRequest.getFirstname());
        newUser.setLastname(registerRequest.getLastname());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setRole(registerRequest.getRole());
        newUser.setAirline(registerRequest.getAirline());
        newUser.setUsername(registerRequest.getUsername());

        userService.save(newUser);

        // Return new User with a 201 CREATED Status
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResult> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userService.findByUsername(loginRequest.getUsername());

        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), loginRequest.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResult("Invalid username or password"));
        }

        User user = userOpt.get();
        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getFirstname(),
                user.getLastname(),
                user.getAirline(),
                user.isFirstLogin()
        );

        AuthResult authResult = new AuthResult(userDTO);

        /*{
              "success": true,
              "user": {
                "username": "admin01",
                "role": "ADMIN",
                "firstName": "John",
                "lastName": "Doe",
                "firstLogin": true
              }
            }*/
        return ResponseEntity.ok(authResult);
    }
}
