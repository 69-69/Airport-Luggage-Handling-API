package com.assigndevelopers.airportluggagehandlingapi.service;

import com.assigndevelopers.airportluggagehandlingapi.model.User;
import com.assigndevelopers.airportluggagehandlingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return  userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return   userRepository.findByEmailAndPassword(email, password);
    }

    public boolean checkPassword(User user, String password) {
        return   user.getPassword().equals(password);
    }

    // Save new user
    public void save(User user) {
        userRepository.save(user);
    }
}
