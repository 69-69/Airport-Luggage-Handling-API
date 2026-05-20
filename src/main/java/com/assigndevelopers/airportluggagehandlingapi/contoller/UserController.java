/*
package com.assigndevelopers.airportluggagehandlingapi.contoller;

import com.assigndevelopers.airportluggagehandlingapi.dto.AuthResultDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.LoginDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.RegisterDTO;
import com.assigndevelopers.airportluggagehandlingapi.dto.UpdatePasswordDTO;
import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {

        User user = userService.create(registerDTO);

        // Return new User with a 201 CREATED Status
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResultDTO> login(@RequestBody LoginDTO dto) {
        User user = userService.login(dto);
        AuthResultDTO authResultDTO = userService.getAuthResult(user, true);
        return ResponseEntity.ok(authResultDTO);
    }

    @PostMapping("/{username}/logout")
    public ResponseEntity<?> logout(@PathVariable String username) {
        userService.logout(username);
        return ResponseEntity.ok(Map.of("message", "User successfully logout"));

    }

    @GetMapping
    public ResponseEntity<List<AuthResultDTO>> getAll() {
        var users = userService.getUsers();

        return users.map(ResponseEntity::ok)
                .orElseThrow(
                        () -> new RuntimeException("No users exist")
                );
    }

    @PutMapping("/{username}/password")
    public ResponseEntity<?> updatePassword(@PathVariable String username, @RequestBody UpdatePasswordDTO dto) {
        userService.updatePassword(username, dto.newPassword(), dto.firstLogin());

        return ResponseEntity.ok(Map.of("message", "User password successfully updated"));
    }

    @DeleteMapping("/{phone}")
    public ResponseEntity<?> delete(@PathVariable String phone) {
        userService.deleteByPhone(phone);

        return ResponseEntity.ok(Map.of("message", "User successfully deleted"));
    }
}
*/
