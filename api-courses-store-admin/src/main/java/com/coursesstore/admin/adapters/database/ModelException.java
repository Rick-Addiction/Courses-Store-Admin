package com.coursesstore.admin.adapters.database;

public class ModelException extends RuntimeException {

    String errorMessage;

    public ModelException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
