package com.example.exceptions;

public class CustomerConflictException extends RuntimeException {

    public CustomerConflictException() {
        super("Customer already registered");
    }

}
