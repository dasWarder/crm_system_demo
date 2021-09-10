package com.example.exception;

public class UserNotFoundException extends Throwable {

  public UserNotFoundException() {}

  public UserNotFoundException(String message) {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
