package com.example.osk.authentication.controller;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.AuthenticationResponse;
import com.example.osk.authentication.RegisterRequest;
import com.example.osk.authentication.service.AuthenticationServiceImpl;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            authenticationService.register(request);
            return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to register user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>("Failed to authenticate user!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/checkTokenValidity")
    public ResponseEntity<Boolean> checkTokenValidity(@RequestHeader(HttpHeaders.AUTHORIZATION) String request, @RequestParam("email") String userEmail) {
        return new ResponseEntity<>(authenticationService.checkTokenValidity(request, userEmail), HttpStatus.OK);
    }

    @GetMapping(path = "/checkEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }
}
