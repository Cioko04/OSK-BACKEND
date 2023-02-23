package com.example.osk.controller;

import com.example.osk.model.User;
import com.example.osk.dto.UserRequest;
import com.example.osk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserRequest>> getUsers() {
        return new ResponseEntity<>(userService.getStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "{email}")
    public ResponseEntity<UserRequest> getUser(@PathVariable("email") String email) {
        return new ResponseEntity<>(userService.getUser(email), HttpStatus.OK);
    }

    @GetMapping(path = "/checkEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam() String email) {
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> registerNewUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{UserId}")
    public ResponseEntity<String> deleteUser(@PathVariable("UserId") Long UserId) {
        userService.deleteUser(UserId);
        return new ResponseEntity<>("User deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String secondName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob) {
        userService.updateUser(
                id,
                name,
                secondName,
                lastName,
                email,
                password,
                dob);

        return new ResponseEntity<>("User updated! ", HttpStatus.OK);
    }
}
