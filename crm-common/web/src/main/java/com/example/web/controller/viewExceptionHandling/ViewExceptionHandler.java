package com.example.web.controller.viewExceptionHandling;

import com.example.exception.*;
import com.example.web.controller.viewExceptionHandling.exception.ExceptionResponse;
import com.example.web.controller.viewExceptionHandling.violation.Violation;
import com.example.web.controller.viewExceptionHandling.violation.ViolationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

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
        TokenRefreshException.class,
        ReportNotFoundException.class,
        BadCredentialsException.class,
        WrongPasswordException.class,
        ResetTokenExpiryException.class,
        PasswordResetTokenNotFoundException.class,
        NotPossibleDeleteException.class
      })
  public ResponseEntity<ExceptionResponse> servicesExceptionsResponse(Exception e) {

    ExceptionResponse response =
        ExceptionResponse.builder()
            .className(
                e.getCause() != null
                    ? e.getCause().getClass().getSimpleName()
                    : e.getClass().getSimpleName())
            .message(e.getCause() != null ? e.getCause().getMessage() : e.getMessage())
            .build();

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(value = {ConstraintViolationException.class})
  public ResponseEntity onConstraintValidationException(
      ConstraintViolationException constraintViolationException) {

    ViolationResponse error = new ViolationResponse();
    Set<ConstraintViolation<?>> constraintViolations =
        constraintViolationException.getConstraintViolations();
    constraintViolations.forEach(
        violation -> {
          Violation responseViolation = new Violation();
          responseViolation.setFieldName(violation.getPropertyPath().toString());
          responseViolation.setMessage(violation.getMessage());
          error.getViolations().add(responseViolation);
        });

    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  public ResponseEntity onMethodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException) {

    ViolationResponse error = new ViolationResponse();
    List<FieldError> fieldErrors =
        methodArgumentNotValidException.getBindingResult().getFieldErrors();
    fieldErrors.forEach(
        fieldError -> {
          Violation responseViolation = new Violation();
          responseViolation.setFieldName(fieldError.getField());
          responseViolation.setMessage(fieldError.getDefaultMessage());
          error.getViolations().add(responseViolation);
        });

    return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
  }
}
