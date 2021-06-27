package com.coursesstore.admin.adapters.http;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import java.util.Set;

@Component
public class RequestValidator<T> {

    public void valid (T requestPutCourse){
        var factory = Validation.buildDefaultValidatorFactory();
        var validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(requestPutCourse);

        if(!violations.isEmpty()){
            throw new ConstraintViolationException(violations);
        }
    }
}
