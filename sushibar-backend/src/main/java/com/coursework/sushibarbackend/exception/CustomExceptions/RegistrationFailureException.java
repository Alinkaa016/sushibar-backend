package com.coursework.sushibarbackend.exception.CustomExceptions;

public class RegistrationFailureException extends RuntimeException {
    public RegistrationFailureException(String message) {
        super(message);
    }
}
