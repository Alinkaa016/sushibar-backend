package com.coursework.sushibarbackend.exception.CustomExceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}

