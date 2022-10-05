package com.example.osk.service;

import com.example.osk.model.Course;
import com.example.osk.model.Student;
import com.example.osk.request.CourseRequest;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface CourseService {

    List<CourseRequest> getCoursesByStudent(Student student);

    void saveCourseForStudent(CourseRequest courseRequest, Long studentId, Long categoryId);

    void deleteCourse(Long id);

    void deleteCoursesByStudent(Student student);

    @Transactional
    void updateCourse(Long id,
                      Date startDate,
                      Integer timeInHours,
                      Integer spendTimeInHours);

}
