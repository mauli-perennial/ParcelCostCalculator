package com.example.parceldeliveryapplication.exceptions;

public class DnsTimeoutException extends RuntimeException{
    public DnsTimeoutException(String message) {
        super(message);
    }
}
