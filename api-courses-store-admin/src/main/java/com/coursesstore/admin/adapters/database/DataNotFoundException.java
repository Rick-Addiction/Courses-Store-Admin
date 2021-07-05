package com.coursesstore.admin.adapters.database;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
