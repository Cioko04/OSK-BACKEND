package com.example.osk.token.repository;

import com.example.osk.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "select t.* from Tokens t inner join Users u on t.user_id = u.id where u.id = :userId", nativeQuery = true)
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
