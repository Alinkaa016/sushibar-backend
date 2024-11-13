package com.coursework.sushibarbackend.exception.CustomExceptions;

public class AuthenticationFailureException extends RuntimeException {
    public AuthenticationFailureException(String message) {
        super(message);
    }
}

