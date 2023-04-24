package com.example.osk.user.repository;

import com.example.osk.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    @Query(value = "select u.* from common_user cm inner join users u on cm.user_id = u.id where cm.id = :id", nativeQuery = true)
    Optional<User> findUserByCommonUserId(Long id);

    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);
}
