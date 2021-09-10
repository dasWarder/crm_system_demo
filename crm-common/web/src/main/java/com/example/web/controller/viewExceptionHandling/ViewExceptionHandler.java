package com.example.web.controller.viewExceptionHandling;

import com.example.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ViewExceptionHandler {

  @ExceptionHandler(
      value = {
        ContactNotFoundException.class,
        TaskNotFoundException.class,
        TodoListNotFoundException.class,
        UnsupportedParameterException.class,
        UserNotFoundException.class,
        UserAlreadyExistException.class,
        AuthorityNotFoundException.class,
        TokenNotFoundException.class,
        TokenRefreshException.class
      })
  public ResponseEntity<ExceptionResponse> servicesExceptionsResponse(Exception e) {

    ExceptionResponse response =
        ExceptionResponse.builder()
            .className(e.getCause().getClass().getSimpleName())
            .message(e.getCause().getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
