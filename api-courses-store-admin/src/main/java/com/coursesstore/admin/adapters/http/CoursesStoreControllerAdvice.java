package com.coursesstore.admin.adapters.http;

import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.ModelException;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CoursesStoreControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(getJSONPropertyName(violation.getRootBeanClass(), violation.getPropertyPath().toString()) + ": " + violation.getMessage());
        }

        var errorResponse = new ErrorResponse(BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundExceptionHandler (DataNotFoundException ex, WebRequest request){

        var errorResponse = new ErrorResponse(NOT_FOUND, ex.getMessage());

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(ModelException.class)
    public ResponseEntity<Object> modelExceptionHandler (ModelException ex, WebRequest request){

        var errorResponse = new ErrorResponse(BAD_REQUEST, ex.getMessage());

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    public static String getJSONPropertyName(Class c, String fieldName){
        try {
            return c.getDeclaredField(fieldName).getAnnotation(JsonProperty.class).value();
        } catch (NoSuchFieldException e) {
            throw new ControllerAdviceException(
                    String.format("Controller Advice Error - Field %s not find on the class %s",
                    fieldName, c.getSimpleName()));
        }
    }

}
