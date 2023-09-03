package com.example.osk.course.repository;

import com.example.osk.category.Category;
import com.example.osk.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository
        extends JpaRepository<Course, Long> {

    Set<Course> findAllBySchoolId(Long schoolId);

    @Query("SELECT DISTINCT c.category FROM Course c where c.school.id = :schoolId")
    Set<Category> getAllCategoriesFromCourseBySchoolId(@Param("schoolId") Long schoolId);
}
