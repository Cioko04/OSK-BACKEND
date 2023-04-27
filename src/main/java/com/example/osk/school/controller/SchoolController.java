package com.example.osk.school.controller;

import com.example.osk.school.SchoolRequest;
import com.example.osk.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/school")
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping()
    public ResponseEntity<List<SchoolRequest>> getSchools() {
        return new ResponseEntity<>(schoolService.getSchools(), HttpStatus.OK);
    }
}
