package com.BMS.exceptions;


public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
