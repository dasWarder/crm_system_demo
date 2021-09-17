package com.example.service.user;

import com.example.exception.PasswordResetTokenNotFoundException;
import com.example.exception.ResetTokenExpiryException;
import com.example.exception.UserNotFoundException;
import com.example.exception.WrongPasswordException;
import com.example.model.user.PasswordResetToken;
import com.example.model.user.User;
import com.example.repository.PasswordResetTokenRepository;
import com.example.service.notification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  private final PasswordEncoder passwordEncoder;

  private final EmailNotificationService mailService;

  private final PasswordResetTokenRepository resetTokenRepository;

  private static final String RESET_PASS_SUBJECT = "Reset password";

  private static final String RESET_PASS_MESSAGE =
      "Your link for reset a password: \n\n%s \n\nWith kind regards, CRM Team";

  private static final String PASS_CHANGED_SUBJECT = "Password was changed";

  private static final String PASS_CHANGED_MESSAGE =
          "Dear %s! \n\nYou password was successfully changed! \n\nWith kind regards, CRM Team";

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
    String message = String.format(RESET_PASS_MESSAGE, resetUrl);
    mailService.sendNotification(email, message, RESET_PASS_SUBJECT);
  }

  @Override
  public String directResetPassword(String token)
      throws PasswordResetTokenNotFoundException, ResetTokenExpiryException {

    log.info("Validate reset token for a user with a token = {}", token);
    PasswordResetToken resetToken = validateResetToken(token);

    if (resetToken.getExpiryDate().isBefore(Instant.now())) {

      throw new ResetTokenExpiryException(
          String.format(
              "A reset token is already expired [%s]", resetToken.getExpiryDate().toString()));
    }

    return resetToken.getToken();
  }

  @Override
  public User resetUserPassword(String password, String confirmPassword, String token)
      throws PasswordResetTokenNotFoundException, WrongPasswordException, UserNotFoundException {

    log.info("Reset user's password");
    PasswordResetToken resetToken = validateResetToken(token);
    User user = resetToken.getUser();

    if (!password.equals(confirmPassword)) {
      throw new WrongPasswordException("Passwords not equals");
    }

    String encodedPass = passwordEncoder.encode(confirmPassword);
    user.setPassword(encodedPass);
    User updatedPassUser = userService.updateUserByEmail(user.getEmail(), user);
    String message = String.format(PASS_CHANGED_MESSAGE, updatedPassUser.getEmail());
    mailService.sendNotification(updatedPassUser.getEmail(), message, RESET_PASS_SUBJECT);

    resetTokenRepository.delete(resetToken);

    return updatedPassUser;
  }

  private PasswordResetToken validateResetToken(String token)
      throws PasswordResetTokenNotFoundException {

    PasswordResetToken resetToken =
        resetTokenRepository
            .findPasswordResetTokenByToken(token)
            .orElseThrow(
                () ->
                    new PasswordResetTokenNotFoundException(
                        String.format(
                            "A password reset token with a token = %s not found", token)));

    return resetToken;
  }
}
