package com.example.exception;

public class TokenNotFoundException extends Throwable {

  public TokenNotFoundException() {}

  public TokenNotFoundException(String message) {
    super(message);
  }

  public TokenNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
