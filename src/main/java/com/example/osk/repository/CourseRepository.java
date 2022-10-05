package com.example.osk.repository;

import com.example.osk.model.Course;
import com.example.osk.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository
        extends JpaRepository<Course, Long> {

    List<Course> findByStudent(Student student);
}
