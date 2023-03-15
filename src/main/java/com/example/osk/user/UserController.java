package com.example.osk.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/users", produces = "application/json")
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

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted! ", HttpStatus.OK);
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
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

    }
}
