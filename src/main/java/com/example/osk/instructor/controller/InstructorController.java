package com.example.osk.instructor.controller;

import com.example.osk.instructor.InstructorRequest;
import com.example.osk.instructor.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/instructor")
public class InstructorController {
    private final InstructorService instructorService;

    @GetMapping(path = "/getInstructorsBySchoolId/{id}")
    public ResponseEntity<List<InstructorRequest>> getInstructors(@PathVariable("id") Long id) {
        return new ResponseEntity<>(instructorService.getInstructorsBySchoolId(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody InstructorRequest instructorRequest) {
        try {
            instructorService.saveInstructor(instructorRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
