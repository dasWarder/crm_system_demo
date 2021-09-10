package com.example.exception;

public class TokenRefreshException extends Throwable {

  public TokenRefreshException() {}

  public TokenRefreshException(String message) {
    super(message);
  }

  public TokenRefreshException(String message, Throwable cause) {
    super(message, cause);
  }
}
