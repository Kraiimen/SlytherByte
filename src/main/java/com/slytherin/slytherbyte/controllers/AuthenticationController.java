package com.slytherin.slytherbyte.controllers;

import com.slytherin.slytherbyte.models.request.AuthenticationRequest;
import com.slytherin.slytherbyte.models.request.RegisterRequest;
import com.slytherin.slytherbyte.models.response.AuthenticationResponse;
import com.slytherin.slytherbyte.models.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private AuthenticationService authService;

    @Autowired
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            Map<String, String> errors = br.getFieldErrors().stream()
                    .map(f -> f.getField() + ": "+ f.getDefaultMessage())
                    .collect(Collectors.toMap(s -> s.split(":")[0], s -> s.split(":")[1]));
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }
}
