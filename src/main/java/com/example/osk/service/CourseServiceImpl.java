package com.example.osk.service;

import com.example.osk.model.Category;
import com.example.osk.model.Course;
import com.example.osk.model.User;
import com.example.osk.repository.CategoryRepository;
import com.example.osk.repository.CourseRepository;
import com.example.osk.repository.UserRepository;
import com.example.osk.dto.CourseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CourseRequest> getCoursesByStudentId(Long userId) {
        List<Course> coursesByStudent = courseRepository.findByStudentId(userId);
        List<CourseRequest> coursesRequest = new ArrayList<>();
        coursesByStudent.forEach(course -> coursesRequest.add(new CourseRequest(
                course.getId(),
                userId,
                course.getCategory().getId(),
                course.getStartDate(),
                course.getSpendTimeInHours(),
                course.isValid())));
        return coursesRequest;
    }

    @Override
    public List<CourseRequest> getCoursesByCategoryId(Long categoryId) {
        List<Course> coursesByStudent = courseRepository.findByCategoryId(categoryId);
        List<CourseRequest> coursesRequest = new ArrayList<>();
        coursesByStudent.forEach(course -> coursesRequest.add(new CourseRequest(
                course.getId(),
                course.getStudent().getId(),
                categoryId,
                course.getStartDate(),
                course.getSpendTimeInHours(),
                course.isValid())));
        return coursesRequest;
    }

    @Override
    public List<CourseRequest> getCoursesByParam(Long studentId, Long categoryId) {
        if (studentId != null) {
            return getCoursesByStudentId(studentId);
        } else if (categoryId != null) {
            return getCoursesByCategoryId(categoryId);
        }
        throw new IllegalArgumentException("There is no param!");
    }

    @Override
    public void saveCourseForStudent(CourseRequest courseRequest, Long studentId, Long categoryId) {
        User student = userRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
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

    private boolean checkIfStudentHasTypeOfCategory(User student, Category category) {
        return student.getCourses().stream().anyMatch(course -> course.getCategory().getCategoryType().equals(category.getCategoryType()));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void deleteCoursesByStudent(User student) {
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
