package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.User;
import com.example.osk.dto.CourseRequest;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface CourseService {

    List<CourseRequest> getCoursesByStudentId(Long studentId);

    List<CourseRequest> getCoursesByCategoryId(Long categoryId);

    List<CourseRequest> getCoursesByParam(Long studentId, Long categoryId);

    void saveCourseForStudent(CourseRequest courseRequest, Long studentId, Long categoryId);

    void deleteCourse(Long id);

    void deleteCoursesByStudent(User student);

    void deleteCoursesByCategory(Category category);

    @Transactional
    void updateCourse(Long id,
                      Date startDate,
                      Integer timeInHours,
                      Integer spendTimeInHours);


}
