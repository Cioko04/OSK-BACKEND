package com.example.osk.school.repository;

import com.example.osk.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository  extends JpaRepository<School, Long> {
    @Query("SELECT s FROM School s LEFT JOIN User u ON s.user = u WHERE u.email = :email")
    Optional<School> findCommonUserByEmail(String email);


}
