package com.example.osk.bookedCourse.controller;

import com.example.osk.bookedCourse.service.BookedCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/bookedCourse")
public class BookedCourseController {
    private final BookedCourseService bookedCourseService;
}
