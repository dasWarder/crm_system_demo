package com.example.exception;

public class PasswordResetTokenNotFoundException extends Throwable {

    public PasswordResetTokenNotFoundException() {
    }

    public PasswordResetTokenNotFoundException(String message) {
        super(message);
    }

    public PasswordResetTokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
