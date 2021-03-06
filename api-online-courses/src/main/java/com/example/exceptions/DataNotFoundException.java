package com.example.exceptions;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super("Data not found in our database");
    }
}
