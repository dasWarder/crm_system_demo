package com.example.repository;

import com.example.model.user.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findPasswordResetTokenByToken(String token);
}
