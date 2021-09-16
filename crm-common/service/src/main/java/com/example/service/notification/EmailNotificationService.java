package com.example.service.notification;

public interface EmailNotificationService {

  void sendRegistrationNotification(String email);

  void sendResetPasswordMessage(String email, String resetUrl);
}
