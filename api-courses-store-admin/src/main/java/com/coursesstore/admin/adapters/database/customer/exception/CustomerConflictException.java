package com.coursesstore.admin.adapters.database.customer.exception;

public class CustomerConflictException extends RuntimeException {

    public CustomerConflictException() {
        super("Customer already registered");
    }

}
