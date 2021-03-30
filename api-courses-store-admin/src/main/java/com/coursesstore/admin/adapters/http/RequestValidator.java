package com.coursesstore.admin.adapters.http;

import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

@Component
public class RequestValidator<T> {

    public void valid (T requestPutCourse){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(requestPutCourse);

        if(!violations.isEmpty()){
            ConstraintViolationException summary = new ConstraintViolationException(violations);
            throw summary;
        }
    }
}
