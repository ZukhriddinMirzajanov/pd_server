package com.example.PD_server.controller;

import com.example.PD_server.request.LoginRequest;
import com.example.PD_server.request.RegisterUserRequest;
import com.example.PD_server.response.AuthenticationResponse;
import com.example.PD_server.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerManager(@RequestBody RegisterUserRequest request) {
        boolean isExists = authService.isEmailAlreadyRegistered(request.getEmail());

        if (isExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginManager(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
