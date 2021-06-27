package com.coursesstore.admin.adapters.http;

import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.customer.exception.CustomerConflictException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class CoursesStoreAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        var errorResponse = new ErrorResponse(BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundExceptionHandler (DataNotFoundException ex, WebRequest request){

        var errorResponse = new ErrorResponse(NOT_FOUND, ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(CustomerConflictException.class)
    public ResponseEntity<Object> customerConflictExceptionHandler (CustomerConflictException ex, WebRequest request){

        var errorResponse = new ErrorResponse(CONFLICT, ex.getLocalizedMessage());

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
