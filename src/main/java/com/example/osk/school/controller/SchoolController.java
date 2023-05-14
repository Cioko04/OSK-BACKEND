package com.example.osk.school.controller;

import com.example.osk.school.SchoolRequest;
import com.example.osk.school.service.SchoolService;
import com.example.osk.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/school")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping()
    public ResponseEntity<List<SchoolRequest>> getSchools() {
        return new ResponseEntity<>(schoolService.getSchools(), HttpStatus.OK);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateSchool(
            @PathVariable("id") Long id,
            @RequestBody SchoolRequest schoolRequest) {
        try {
            schoolService.updateSchool(id, schoolRequest);
            return new ResponseEntity<>("School updated! ", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping(path = "delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        schoolService.deleteSchool(id);
    }
}
