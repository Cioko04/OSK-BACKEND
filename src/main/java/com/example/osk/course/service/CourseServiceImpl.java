package com.example.osk.course.service;

import com.example.osk.category.Category;
import com.example.osk.category.CategoryType;
import com.example.osk.category.service.CategoryService;
import com.example.osk.course.Course;
import com.example.osk.course.CourseRequest;
import com.example.osk.course.repository.CourseRepository;
import com.example.osk.instructor.service.InstructorService;
import com.example.osk.school.School;
import com.example.osk.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
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
    public void saveCourse(CourseRequest courseRequest) {
        School school = schoolService.getSchoolById(courseRequest.getSchoolId());
        Category category = categoryService.getCategory(CategoryType.getCategoryTypeFromString(courseRequest.getCategoryType()));

        if (!hasSchoolTypeOfCourse(school.getCourses(), CategoryType.getCategoryTypeFromString(courseRequest.getCategoryType()))) {
            Course course = Course.builder()
                    .price(courseRequest.getPrice())
                    .description(courseRequest.getDescription())
                    .school(school)
                    .category(category)
                    .build();

            courseRepository.save(course);
        } else {
            throw new DuplicateKeyException("User already has this type of course");
        }
    }

    @Override
    public void updateCourse(CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseRequest.getId()).orElseThrow(() -> new IllegalStateException(
                "Course with id " + courseRequest.getId() + " does not exist"));

        if (courseRequest.getPrice() != null &&
                !Objects.equals(course.getPrice(), courseRequest.getPrice())) {
            course.setPrice(courseRequest.getPrice());
        }

        if (courseRequest.getDescription() != null &&
                courseRequest.getDescription().length() > 0 &&
                !Objects.equals(course.getDescription(), courseRequest.getDescription())) {
            course.setDescription(courseRequest.getDescription());
        }

        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private boolean hasSchoolTypeOfCourse(Set<Course> courses, CategoryType categoryType) {
        return courses.stream().anyMatch(course -> categoryType.equals(course.getCategory().getCategoryType()));
    }
}
