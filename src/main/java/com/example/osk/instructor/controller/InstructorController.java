package com.example.osk.instructor.controller;

import com.example.osk.instructor.InstructorRequest;
import com.example.osk.instructor.service.InstructorService;
import com.example.osk.school.SchoolRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping(path = "/getInstructorsBySchoolId/{id}")
    public ResponseEntity<List<InstructorRequest>> getInstructorsBySchoolId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(instructorService.getInstructorsBySchoolId(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody InstructorRequest instructorRequest) {
        try {
            instructorService.saveInstructor(instructorRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/updateInstructor")
    public ResponseEntity<String> updateInstructor(
            @RequestBody InstructorRequest instructorRequest) {
        try {
            instructorService.updateInstructor(instructorRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping(path = "deleteInstructorById/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable("id") Long id) {
        try {
            instructorService.deleteInstructorById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user!", HttpStatus.CONFLICT);
        }
    }

}
