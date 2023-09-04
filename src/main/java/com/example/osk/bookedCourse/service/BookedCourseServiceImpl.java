package com.example.osk.bookedCourse.service;

import com.example.osk.bookedCourse.repository.BookedCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookedCourseServiceImpl implements BookedCourseService {
    private final BookedCourseRepository bookedCourseRepository;
}
