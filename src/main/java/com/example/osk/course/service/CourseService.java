package com.example.osk.course.service;

import com.example.osk.course.CourseRequest;

import java.util.Set;

public interface CourseService {

    Set<CourseRequest> getAllCoursesForSchool(Long schoolId);

    CourseRequest saveCourse(CourseRequest courseRequest);

    CourseRequest updateCourse(CourseRequest courseRequest);

    Long deleteCourse(Long id);
}
