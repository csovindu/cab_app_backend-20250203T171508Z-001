package com.cabservice.backend.services;

import com.cabservice.backend.models.User;
import com.cabservice.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public String register(User user, boolean isStaffUser) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already in use!";
        }
        user.setUserrole(isStaffUser ? 1 : 2);
        userRepository.save(user);
        return "User registered successfully!";
    }

    public Map<String, Object> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful!");
            response.put("userrole", user.get().getUserrole());
            response.put("userid", user.get().getId());
            return response;
        }
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("message", "Invalid email or password!");
        return errorResponse;
    }

    // Get all users
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getStaffUsers() {
        return userRepository.findByUserrole(1); // Get only staff users
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean deleteUser(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

