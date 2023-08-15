package com.example.osk.course.service;

import com.example.osk.course.CourseRequest;

import java.util.Set;

public interface CourseService {

    Set<CourseRequest> getAllCoursesForSchool(Long schoolId);

    void saveCourse(CourseRequest courseRequest);

    void updateCourse(CourseRequest courseRequest);

    void deleteCourse(Long id);
}
