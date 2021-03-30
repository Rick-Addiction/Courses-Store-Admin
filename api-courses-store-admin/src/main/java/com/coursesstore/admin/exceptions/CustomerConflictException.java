package com.coursesstore.admin.exceptions;

public class CustomerConflictException extends RuntimeException {

    public CustomerConflictException() {
        super("Customer already registered");
    }

}
