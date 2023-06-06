package com.example.osk.instructor.repository;

import com.example.osk.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InstructorRepository
        extends JpaRepository<Instructor, Long> {

    Set<Instructor> findAllBySchoolId(Long id);
}
