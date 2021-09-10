package com.example.exception;

public class ReportNotFoundException extends Throwable {

    public ReportNotFoundException() {
    }

    public ReportNotFoundException(String message) {
        super(message);
    }

    public ReportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
