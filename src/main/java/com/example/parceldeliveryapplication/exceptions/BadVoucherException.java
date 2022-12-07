package com.example.parceldeliveryapplication.exceptions;

public class BadVoucherException extends RuntimeException{
    public BadVoucherException (String message){
        super(message);
    }
}
