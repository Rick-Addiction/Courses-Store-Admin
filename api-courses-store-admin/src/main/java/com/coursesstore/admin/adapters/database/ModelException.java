package com.coursesstore.admin.adapters.database;

public class ModelException extends RuntimeException {

    final String errorMessage;

    public ModelException(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
