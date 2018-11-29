package com.dicka.springbootemail;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerifyTokenRepository extends JpaRepository<VerifyToken, Long> {

    List<VerifyToken> findByUsersEmail(String email);
    List<VerifyToken> findByToken(String token);

}
