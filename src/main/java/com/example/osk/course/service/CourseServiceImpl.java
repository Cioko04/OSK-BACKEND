package com.example.osk.course.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import com.example.osk.category.service.CategoryService;
import com.example.osk.course.Course;
import com.example.osk.course.CourseRequest;
import com.example.osk.course.repository.CourseRepository;
import com.example.osk.instructor.Instructor;
import com.example.osk.instructor.service.InstructorService;
import com.example.osk.user.User;
import com.example.osk.user.service.UserService;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public void addCourse(CourseRequest courseRequest) {
        Instructor instructor = instructorService.getInstructorById(courseRequest.getInstructorId());
        Category category = categoryService.getCategory(courseRequest.getCategoryType());
        User user = userService.findUserByIdIfExists(courseRequest.getUserId());

        if (!hasUserTypeOfCourse(user.getCourses(), courseRequest.getCategoryType())) {
            Course course = Course.builder()
                    .startDate(LocalDate.now())
                    .category(category)
                    .user(user)
                    .instructor(instructor)
                    .build();

            courseRepository.save(course);
        } else {
            throw new DuplicateKeyException("User already has this type of course");
        }
    }

    private boolean hasUserTypeOfCourse(Set<Course> courses, CategoryType categoryType) {
        return courses.stream().anyMatch(course -> categoryType.equals(course.getCategory().getCategoryType()));
    }
}
