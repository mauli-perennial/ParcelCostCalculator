package com.example.parceldeliveryapplication.exceptions;

/**
 * This Exception is thrown when user provides the invalid data
 */
public class InvalidParcelException extends RuntimeException {
    public InvalidParcelException(String message) {
        super(message);
    }
}
