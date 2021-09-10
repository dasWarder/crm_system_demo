package com.example.exception;

public class TodoListNotFoundException extends Throwable {

  public TodoListNotFoundException() {}

  public TodoListNotFoundException(String message) {
    super(message);
  }

  public TodoListNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
