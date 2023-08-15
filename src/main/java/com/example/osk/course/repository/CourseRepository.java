package com.example.osk.course.repository;

import com.example.osk.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository
        extends JpaRepository<Course, Long> {

    Set<Course> findAllBySchoolId(Long schoolId);
}
