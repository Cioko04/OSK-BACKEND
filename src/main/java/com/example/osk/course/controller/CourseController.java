package com.example.osk.course.controller;

import com.example.osk.course.Course;
import com.example.osk.course.CourseRequest;
import com.example.osk.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/course")
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<String> register(@RequestBody CourseRequest courseRequest) {
        try {
            courseService.addCourse(courseRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
