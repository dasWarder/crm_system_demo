package com.example.repository;

import com.example.model.user.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

  Optional<Token> getTokenByToken(String token);

  void deleteTokenBySubject(String subject);
}
