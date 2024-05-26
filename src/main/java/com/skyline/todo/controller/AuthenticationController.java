package com.skyline.todo.controller;

import com.skyline.todo.model.auth.AuthenticationRequest;
import com.skyline.todo.model.auth.AuthenticationResponse;
import com.skyline.todo.model.auth.RegisterRequest;
import com.skyline.todo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        service.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse authResponse = service.authenticate(request);
        if(authResponse == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        AuthenticationResponse authResponse = service.refreshToken(request, response);
        if(authResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(authResponse);
        }
    }

    @PostMapping("/logout_all_devices")
    public ResponseEntity<?> logoutAllDevices(
            HttpServletRequest request, HttpServletResponse response
    ) {
        service.logOutOfALlDevices(request, response);
        return ResponseEntity.ok().build();
    }
}