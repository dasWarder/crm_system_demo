package com.example.controller.viewExceptionHandling;

import com.example.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ViewExceptionHandler {


    @ExceptionHandler(value = {
            ContactNotFoundException.class,
            TaskNotFoundException.class,
            TodoListNotFoundException.class,
            UnsupportedParameterException.class,
            UserNotFoundException.class,
            UserAlreadyExistException.class
    })
    public ExceptionResponse servicesExceptionsResponse(Exception e) {

        ExceptionResponse response = ExceptionResponse.builder()
                                                        .className(e.getCause().getClass().getSimpleName())
                                                        .message(e.getCause().getMessage())
                                                        .build();

        return response;
    }
}
