package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.Course;
import com.example.osk.model.Student;
import com.example.osk.repository.CategoryRepository;
import com.example.osk.repository.CourseRepository;
import com.example.osk.repository.StudentRepository;
import com.example.osk.request.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CourseRequest> getCoursesByStudent(Student student) {
        List<Course> coursesByStudent = courseRepository.findByStudent(student);
        List<CourseRequest> coursesRequest = new ArrayList<>();
        coursesByStudent.forEach(course -> coursesRequest.add(new CourseRequest(course)));
        return coursesRequest;
    }
    @Override
    public List<CourseRequest> getCoursesByCategory(Category category) {
        List<Course> coursesByStudent = courseRepository.findByCategory(category);
        List<CourseRequest> coursesRequest = new ArrayList<>();
        coursesByStudent.forEach(course -> coursesRequest.add(new CourseRequest(course)));
        return coursesRequest;
    }

    @Override
    public void saveCourseForStudent(CourseRequest courseRequest, Long studentId, Long categoryId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + " does not exist"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException(
                "student with id " + studentId + " does not exist"));
        if (!checkIfStudentHasTypeOfCategory(student, category)) {
            Course course = new Course();
            course.setStudent(student);
            course.setCategory(category);
            course.setStartDate(courseRequest.getStartDate());
            if (courseRequest.getSpendTimeInHours() != null &&
                    courseRequest.getSpendTimeInHours() > 0) {
                course.setSpendTimeInHours(courseRequest.getSpendTimeInHours());
            } else {
                course.setSpendTimeInHours(0);
            }
            courseRepository.save(course);
        } else {
            throw new IllegalArgumentException("Student already has course with that type!");
        }

    }

    private boolean checkIfStudentHasTypeOfCategory(Student student, Category category) {
        return student.getCourses().stream().anyMatch(course -> course.getCategory().getCategoryType().equals(category.getCategoryType()));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteCoursesByStudent(Student student) {
        courseRepository.deleteAll(student.getCourses());
    }

    @Override
    public void deleteCoursesByCategory(Category category) {
        courseRepository.deleteAll(category.getCourses());
    }

    @Override
    @Transactional
    public void updateCourse(Long id,
                             Date startDate,
                             Integer timeInHours,
                             Integer spendTimeInHours) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "course with id " + id + " does not exist"));
        if (startDate != null &&
                !Objects.equals(course.getStartDate(), startDate)) {
            course.setStartDate(startDate);
        }
        if (spendTimeInHours != null &&
                !Objects.equals(course.getSpendTimeInHours(), spendTimeInHours)) {
            course.setSpendTimeInHours(spendTimeInHours);
        }
        courseRepository.save(course);
    }

}
