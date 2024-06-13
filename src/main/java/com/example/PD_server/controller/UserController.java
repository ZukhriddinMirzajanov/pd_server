package com.example.PD_server.controller;

import com.example.PD_server.model.User;
import com.example.PD_server.request.RegisterUserRequest;
import com.example.PD_server.service.AuthService;
import com.example.PD_server.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(userService.getUserById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody RegisterUserRequest request) {
        Optional<User> userOptional = userService.getUserById(id);
        boolean isExists = authService.isEmailAlreadyRegistered(request.getEmail());
        User user = userOptional.get();

        if (userOptional.isPresent() && !isExists) {

            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());
            if (request.getPassword() != null && !request.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
            }
            user.setRole(request.getRole());

            userService.updateUser(user);

            return ResponseEntity.ok(user);
        } else if (isExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
