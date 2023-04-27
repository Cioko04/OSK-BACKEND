package com.example.osk.user.controller;

import com.example.osk.user.Role;
import com.example.osk.user.UserRequest;
import com.example.osk.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/getUserByEmail")
    public ResponseEntity<?> getUser(@RequestParam("email") String email) {
        try {
        return new ResponseEntity<>(userService.getUser(email), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to register user!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequest userRequest) {
        try {
            userService.updateUser(userRequest);
            return new ResponseEntity<>("User updated! ", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
