package com.example.osk.user.repository;

import com.example.osk.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u JOIN School s ON u = s.user")
    Set<User> findUsersWithSchool();
}
