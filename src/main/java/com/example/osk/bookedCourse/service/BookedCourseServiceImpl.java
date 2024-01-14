package com.example.osk.bookedCourse.service;

import com.example.osk.bookedCourse.repository.BookedCourseRepository;
import org.springframework.stereotype.Service;

@Service
public record BookedCourseServiceImpl(
        BookedCourseRepository bookedCourseRepository) implements BookedCourseService {
}
