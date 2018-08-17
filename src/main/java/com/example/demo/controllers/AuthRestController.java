package com.example.demo.controllers;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.security.CurrentUser;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserPrincipal;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "*") // TODO change me
public class AuthRestController {

    private UserService service;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    public AuthRestController(UserService service, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping(value = "/check")
    public UserPrincipal checkAuth(@CurrentUser UserPrincipal currentUser) {
        return currentUser;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody SignInRequest signInRequest) {
        String jwt = service.authenticate(
                signInRequest.getUsername(), signInRequest.getPassword()
        );
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
