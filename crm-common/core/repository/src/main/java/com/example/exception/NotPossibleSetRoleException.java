package com.example.exception;

public class NotPossibleSetRoleException extends Throwable {

    public NotPossibleSetRoleException() {
    }

    public NotPossibleSetRoleException(String message) {
        super(message);
    }

    public NotPossibleSetRoleException(String message, Throwable cause) {
        super(message, cause);
    }
}
