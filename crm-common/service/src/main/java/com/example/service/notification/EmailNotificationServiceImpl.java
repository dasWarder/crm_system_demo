package com.example.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationServiceImpl implements EmailNotificationService {

  @Value("${email.author}")
  private String author;

  private final JavaMailSender javaMailSender;

  private static final String REGISTER_SUBJECT = "Registration";

  private static final String REGISTRATION_MESSAGE =
      "Dear, %s! \n\nThank you for registration on our service. \n\nWith kind regards, CRM Team";

  private static final String RESET_PASS_SUBJECT = "Reset password";

  private static final String RESET_PASS_MESSAGE =
      "Your link for reset a password: \n\n%s \n\nWith kind regards, CRM team";

  @Override
  public void sendRegistrationNotification(String email) {

    log.info("Send an email notification about registration on the email = {}", email);

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(author);
    message.setSubject(REGISTER_SUBJECT);
    message.setTo(email);
    message.setText(String.format(REGISTRATION_MESSAGE, email));

    javaMailSender.send(message);
  }

  @Override
  public void sendResetPasswordMessage(String email, String resetUrl) {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(author);
    message.setSubject(RESET_PASS_SUBJECT);
    message.setTo(email);
    message.setText(String.format(RESET_PASS_MESSAGE, resetUrl));

    javaMailSender.send(message);
  }
}
