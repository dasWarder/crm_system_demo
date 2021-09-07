package com.example.exception;

public class UnsupportedParameterException extends Throwable {

    public UnsupportedParameterException() {
    }

    public UnsupportedParameterException(String message) {
        super(message);
    }

    public UnsupportedParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
