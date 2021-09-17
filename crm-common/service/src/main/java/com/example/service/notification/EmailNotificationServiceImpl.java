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

  @Override
  public void sendNotification(String email, String message, String subject) {

    log.info("Send a new email notification about {}", subject);

    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setFrom(author);
    simpleMailMessage.setSubject(subject);
    simpleMailMessage.setTo(email);
    simpleMailMessage.setText(message);

    javaMailSender.send(simpleMailMessage);
  }
}
