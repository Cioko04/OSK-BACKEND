package com.example.osk.controller;

import com.example.osk.request.CourseRequest;
import com.example.osk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseRequest>> getCoursesByParam(@RequestParam(required = false) Long studentId, @RequestParam(required = false) Long categoryId){
        return new ResponseEntity<>(courseService.getCoursesByParam(studentId,categoryId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewCourse(@RequestParam Long studentId, @RequestParam Long categoryId, @Valid @RequestBody CourseRequest courseRequest){
        courseService.saveCourseForStudent(courseRequest, studentId, categoryId);
        return new ResponseEntity<>("Record saved! ", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("Record deleted! ", HttpStatus.OK);
    }

    @PutMapping(path = "{courseId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("courseId") Long courseId,
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Integer timeInHours,
            @RequestParam(required = false) Integer spendTimeInHours) {
        courseService.updateCourse(
                courseId,
                startDate,
                timeInHours,
                spendTimeInHours);
        return new ResponseEntity<>("Record updated! ", HttpStatus.OK);
    }

}
