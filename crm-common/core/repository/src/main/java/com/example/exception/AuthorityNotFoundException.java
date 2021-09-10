package com.example.exception;

public class AuthorityNotFoundException extends Throwable {

  public AuthorityNotFoundException() {}

  public AuthorityNotFoundException(String message) {
    super(message);
  }

  public AuthorityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
