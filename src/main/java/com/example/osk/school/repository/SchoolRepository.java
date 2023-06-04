package com.example.osk.school.repository;

import com.example.osk.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository
        extends JpaRepository<School, Long> {

}
