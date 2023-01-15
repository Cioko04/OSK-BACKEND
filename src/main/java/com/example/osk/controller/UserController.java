package com.example.osk.controller;

import com.example.osk.model.User;
import com.example.osk.request.UserRequest;
import com.example.osk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserRequest>> getUsers() {
        return new ResponseEntity<>(userService.getStudents(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserRequest> getUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/checkEmail")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam() String email) {
        return new ResponseEntity<>(userService.existsByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
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
