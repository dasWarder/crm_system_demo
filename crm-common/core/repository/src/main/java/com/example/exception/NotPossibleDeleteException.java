package com.example.exception;

public class NotPossibleDeleteException extends Throwable {

    public NotPossibleDeleteException() {
    }

    public NotPossibleDeleteException(String message) {
        super(message);
    }

    public NotPossibleDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
