package com.example.exception;

public class ResetTokenExpiryException extends Throwable {

    public ResetTokenExpiryException() {
    }

    public ResetTokenExpiryException(String message) {
        super(message);
    }

    public ResetTokenExpiryException(String message, Throwable cause) {
        super(message, cause);
    }
}
