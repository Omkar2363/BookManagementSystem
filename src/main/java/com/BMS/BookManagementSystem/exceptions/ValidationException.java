package com.BMS.BookManagementSystem.exceptions;


public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
