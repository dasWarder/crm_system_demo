package com.example.exception;

public class ContactNotFoundException extends Throwable {

  public ContactNotFoundException() {}

  public ContactNotFoundException(String message) {
    super(message);
  }

  public ContactNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
