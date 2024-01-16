package com.example.osk.course.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import com.example.osk.category.service.CategoryService;
import com.example.osk.common.FieldUpdatable;
import com.example.osk.course.Course;
import com.example.osk.course.CourseRequest;
import com.example.osk.course.repository.CourseRepository;
import com.example.osk.instructor.service.InstructorService;
import com.example.osk.school.School;
import com.example.osk.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService, FieldUpdatable {
    private final CourseRepository courseRepository;
    private final SchoolService schoolService;
    private final CategoryService categoryService;
    private final InstructorService instructorService;

    @Override
    public Set<CourseRequest> getAllCoursesForSchool(Long schoolId) {
        Set<CourseRequest> courses = courseRepository.findAllBySchoolId(schoolId).stream()
                .map(CourseRequest::new)
                .collect(Collectors.toSet());
        courses.forEach(courseRequest -> {
            courseRequest.setStudentCount(0L);
            courseRequest.setInstructorCount(instructorService.countInstructorByCourseAndSchool(courseRequest.getCategoryType(), courseRequest.getSchoolId()));
        });
        return courses;
    }

    @Override
    public CourseRequest saveCourse(CourseRequest courseRequest) {
        School school = schoolService.getSchoolById(courseRequest.getSchoolId());
        Category category = categoryService.getCategory(CategoryType.getCategoryTypeFromString(courseRequest.getCategoryType()));

        if (!hasSchoolTypeOfCourse(school.getCourses(), category.getCategoryType())) {
            Course course = Course.builder()
                    .price(courseRequest.getPrice())
                    .description(courseRequest.getDescription())
                    .school(school)
                    .category(category)
                    .build();

            Course savedCourse = courseRepository.save(course);
            return new CourseRequest(savedCourse);
        } else {
            throw new DuplicateKeyException("User already has this type of course: " + category.getCategoryType());
        }
    }

    @Override
    public CourseRequest updateCourse(CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseRequest.getId()).orElseThrow(() -> new IllegalStateException(
                "Course with id " + courseRequest.getId() + " does not exist"));

        updateFieldIfChanged(courseRequest.getPrice(), course.getPrice(), course::setPrice);
        updateFieldIfChanged(courseRequest.getDescription(), course.getDescription(), course::setDescription);

        Course savedCourse = courseRepository.save(course);
        return new CourseRequest(savedCourse);
    }

    @Override
    public Long deleteCourse(Long id) {
        courseRepository.deleteById(id);
        return id;
    }

    private boolean hasSchoolTypeOfCourse(Set<Course> courses, CategoryType categoryType) {
        return courses.stream().anyMatch(course -> categoryType.equals(course.getCategory().getCategoryType()));
    }
}
