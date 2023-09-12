package com.example.osk.course.controller;

import com.example.osk.course.CourseRequest;
import com.example.osk.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping(path = "/getCoursesBySchoolId/{id}")
    public ResponseEntity<Set<CourseRequest>> getCoursesBySchoolId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(courseService.getAllCoursesForSchool(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody CourseRequest courseRequest) {
        try {
            courseService.saveCourse(courseRequest);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/updateCourse")
    public ResponseEntity<String> updateInstructor(
            @RequestBody CourseRequest courseRequest) {
        try {
            courseService.updateCourse(courseRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "deleteCourseById/{id}")
    public ResponseEntity<String> deleteInstructorById(@PathVariable("id") Long id) {
        try {
            courseService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
