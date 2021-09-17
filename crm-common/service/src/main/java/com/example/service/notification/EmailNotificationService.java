package com.example.service.notification;

public interface EmailNotificationService {

  void sendNotification(String email, String message, String subject);
}
