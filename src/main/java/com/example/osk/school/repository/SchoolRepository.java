package com.example.osk.school.repository;

import com.example.osk.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SchoolRepository
        extends JpaRepository<School, Long> {

    @Query("SELECT DISTINCT s.city FROM School s")
    Set<String> getAllCities();

}
