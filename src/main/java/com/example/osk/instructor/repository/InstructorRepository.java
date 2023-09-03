package com.example.osk.instructor.repository;

import com.example.osk.category.CategoryType;
import com.example.osk.instructor.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface InstructorRepository
        extends JpaRepository<Instructor, Long> {

    Set<Instructor> findAllBySchoolId(Long id);

    @Query("SELECT COUNT(i) FROM Instructor i " +
            "JOIN i.categories c " +
            "WHERE i.school.id = :schoolId " +
            "AND c.categoryType = :categoryType")
    Long countInstructorByCategoryAndSchool(@Param("categoryType") CategoryType categoryType, @Param("schoolId") Long schoolId);

    @Query("SELECT COUNT(i) FROM Instructor i " +
            "WHERE i.school.id = :schoolId")
    Long countInstructorBySchool(@Param("schoolId") Long schoolId);
}
