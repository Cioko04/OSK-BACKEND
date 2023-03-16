package com.example.osk.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "select t.* from Token t inner join Users u on t.user_id = u.id where u.id = :userId and (t.expired = false or t.revoked = false)", nativeQuery = true)
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}