package com.example.osk.commonUser.repository;

import com.example.osk.commonUser.CommonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {

    @Query(value = "select cm.* from common_user cm inner join users u on cm.user_id = u.id where u.email = :email", nativeQuery = true)
    Optional<CommonUser> findCommonUserByEmail(String email);
}
