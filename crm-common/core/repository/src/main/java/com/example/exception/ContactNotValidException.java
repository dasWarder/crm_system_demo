package com.example.exception;

public class ContactNotValidException extends Throwable {

    public ContactNotValidException() {
    }

    public ContactNotValidException(String message) {
        super(message);
    }

    public ContactNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
