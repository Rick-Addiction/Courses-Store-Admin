package com.coursesstore.admin.adapters.http;

public class ControllerAdviceException extends RuntimeException {
    public ControllerAdviceException(String errorMessage){
        super(errorMessage);
    }
}
