package com.example.osk.authentication.controller;

import com.example.osk.authentication.AuthenticationRequest;
import com.example.osk.authentication.RegisterRequest;
import com.example.osk.authentication.service.AuthenticationServiceImpl;
import com.example.osk.school.SchoolRequest;
import com.example.osk.school.service.SchoolService;
import com.example.osk.user.Role;
import com.example.osk.user.User;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;
    private final SchoolService schoolService;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }catch (BadCredentialsException e) {
            return new ResponseEntity<>("Failed to authenticate user!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SchoolRequest request) {
        try {
                schoolService.saveSchool(request);
                return new ResponseEntity<>("School registered!", HttpStatus.CREATED);

        }catch (Exception e) {
            return new ResponseEntity<>("Failed to register school!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody UserRequest request) {
//        try {
//            request.setRole(Role.USER);
//            userService.saveUser(request);
//            return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
//        }catch (Exception e) {
//            return new ResponseEntity<>("Failed to register user!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/checkTokenValidity")
    public ResponseEntity<Boolean> checkTokenValidity(@RequestHeader(HttpHeaders.AUTHORIZATION) String request, @RequestParam("email") String userEmail) {
            return new ResponseEntity<>(authenticationService.checkTokenValidity(request, userEmail), HttpStatus.OK);
    }

    @GetMapping(path = "/checkEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }
}
