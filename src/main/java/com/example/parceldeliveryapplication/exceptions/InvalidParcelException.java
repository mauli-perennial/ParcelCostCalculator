package com.example.parceldeliveryapplication.exceptions;

public class InvalidParcelException extends RuntimeException {
    public InvalidParcelException(String message) {
        super(message);
    }
}
