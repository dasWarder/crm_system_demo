package com.example.service.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailNotificationServiceImpl implements EmailNotificationService {

  @Value("${email.author}")
  private String author;

  @Autowired
  private JavaMailSender javaMailSender;

  private static final String SUBJECT = "Registration";

  private static final String REGISTRATION_MESSAGE =
      "Dear, %s! \n\nThank you for registration on our service. \n\nWith kind regards, CRM Team";

  @Override
  public void sendRegistrationNotification(String email) {

    log.info("Send an email notification about registration on the email = {}", email);

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(author);
    message.setSubject(SUBJECT);
    message.setTo(email);
    message.setText(String.format(REGISTRATION_MESSAGE, email));

    javaMailSender.send(message);
  }
}
