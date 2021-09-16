package com.example.service.user;

import com.example.exception.UserNotFoundException;
import com.example.model.user.PasswordResetToken;
import com.example.model.user.User;
import com.example.repository.PasswordResetTokenRepository;
import com.example.service.notification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

  @Value("${url.base}")
  private String baseUrl;

  private final UserService userService;

  private final EmailNotificationService mailService;

  private PasswordResetTokenRepository resetTokenRepository;

  @Override
  public void processForgotPassword(String email) throws UserNotFoundException {

    log.info("Precess forgot password operation for a user with the mail = {}", email);
    User userByEmail = userService.getUserByEmail(email);
    PasswordResetToken resetToken =
        PasswordResetToken.builder()
            .token(UUID.randomUUID().toString())
            .user(userByEmail)
            .expiryDate(Instant.now().minusSeconds(1800))
            .build();

    PasswordResetToken storedResetToken = resetTokenRepository.save(resetToken);

    String resetUrl = String.format("%s/reset?token=%s", baseUrl, storedResetToken.getToken());
    mailService.sendResetPasswordMessage(email, resetUrl);
  }

  @Override
  public User resetPassword(String email, String oldPassword, String password) {

    log.info("Reset password for a user with the mail = {}", email);

    return null;
  }
}
