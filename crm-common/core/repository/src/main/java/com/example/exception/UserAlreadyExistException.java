package com.example.exception;

public class UserAlreadyExistException extends Throwable {

  public UserAlreadyExistException() {}

  public UserAlreadyExistException(String message) {
    super(message);
  }

  public UserAlreadyExistException(String message, Throwable cause) {
    super(message, cause);
  }
}
