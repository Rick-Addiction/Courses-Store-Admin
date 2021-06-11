package com.coursesstore.admin.adapters.database;

public class DataNotFoundException extends RuntimeException {

    String errorMessage;

    public DataNotFoundException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
