package com.coursework.sushibarbackend.exception.CustomExceptions;

public class PaymentFailureException extends RuntimeException {
    public PaymentFailureException(String message) {
        super(message);
    }
}

