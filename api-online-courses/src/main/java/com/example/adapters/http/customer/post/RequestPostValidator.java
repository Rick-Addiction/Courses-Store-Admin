package com.example.adapters.http.customer.post;


import com.example.adapters.http.customer.post.dto.RequestPostCustomer;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

@Component
public class RequestPostValidator {


    void valid(RequestPostCustomer requestPostCustomer) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestPostCustomer>> violations = validator.validate(requestPostCustomer);

        if(!violations.isEmpty()){
            ConstraintViolationException summary = new ConstraintViolationException(violations);
            throw summary;
        }
    }

}
