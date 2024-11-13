package com.coursework.sushibarbackend.exception.CustomExceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}

