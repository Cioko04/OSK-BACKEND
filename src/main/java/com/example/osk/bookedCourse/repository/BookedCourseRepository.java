package com.example.osk.bookedCourse.repository;

import com.example.osk.bookedCourse.BookedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedCourseRepository
        extends JpaRepository<BookedCourse, Long> {
}
