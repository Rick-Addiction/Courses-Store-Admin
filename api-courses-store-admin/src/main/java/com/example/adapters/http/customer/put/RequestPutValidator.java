package com.example.adapters.http.customer.put;

import com.example.adapters.http.customer.put.dto.RequestPutCustomer;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

@Component
public class RequestPutValidator {

    void valid (RequestPutCustomer requestPutCustomer){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RequestPutCustomer>> violations = validator.validate(requestPutCustomer);

        if(!violations.isEmpty()){
            ConstraintViolationException summary = new ConstraintViolationException(violations);
            throw summary;
        }
    }
}
