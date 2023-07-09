package com.example.osk.school.controller;

import com.example.osk.school.School;
import com.example.osk.school.SchoolRequest;
import com.example.osk.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/school")
public class SchoolController {
    private final SchoolService schoolService;

    // Get
    @GetMapping(path = "/getSchools")
    public ResponseEntity<List<SchoolRequest>> getSchools() {
        return new ResponseEntity<>(schoolService.getSchools(), HttpStatus.OK);
    }

    @GetMapping(path = "/getCities")
    public ResponseEntity<Set<String>> getCities() {
        return new ResponseEntity<>(schoolService.getCities(), HttpStatus.OK);
    }

    @GetMapping(path = "/getSchoolByCitiesAndCategories")
    public ResponseEntity<Set<SchoolRequest>> getSchoolByCitiesAndCategories(
            @RequestParam(value = "cities") Set<String> cities,
            @RequestParam(value = "categories") Set<String> categories) {
        return new ResponseEntity<>(schoolService.getSchoolsByCitiesAndCategories(cities, categories), HttpStatus.OK);
    }
    // Post
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SchoolRequest schoolRequest) {
        try {
            schoolService.saveSchool(schoolRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Put
    @PutMapping(path = "/updateSchool")
    public ResponseEntity<String> updateSchool(
            @RequestBody SchoolRequest schoolRequest) {
        try {
            schoolService.updateSchool(schoolRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    // Delete
    @DeleteMapping(path = "deleteById/{id}")
    public void deleteSchool(@PathVariable("id") Long id) {
        schoolService.deleteSchool(id);
    }


}
