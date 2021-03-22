package com.example.adapters.database.customer.exception;

public class CustomerConflictException extends RuntimeException {

    public CustomerConflictException() {
        super("Customer already registered");
    }

}
