package com.example.osk.commonUser.controller;

import com.example.osk.authentication.RegisterRequest;
import com.example.osk.commonUser.CommonUserRequest;
import com.example.osk.commonUser.service.CommonUserService;
import com.example.osk.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/common-users")
public class CommonUserController {
    private final CommonUserService commonUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CommonUserRequest request) {
        try {
            commonUserService.saveCommonUser(request);
            return new ResponseEntity<>("User registered!", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>("Failed to register user!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getUserByEmail")
    public ResponseEntity<UserRequest> getUser(@RequestParam("email") String email) {
        return new ResponseEntity<>(commonUserService.getCommonUser(email), HttpStatus.OK);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateUser(
            @PathVariable("id") Long id,
            @RequestBody CommonUserRequest commonUserRequest) {
        try {
            commonUserService.updateCommonUser(commonUserRequest);
            return new ResponseEntity<>("User updated! ", HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
