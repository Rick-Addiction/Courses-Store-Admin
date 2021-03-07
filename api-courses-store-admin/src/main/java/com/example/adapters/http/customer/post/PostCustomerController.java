package com.example.adapters.http.customer.post;

import com.example.adapters.http.customer.post.dto.PostCustomerConverter;
import com.example.adapters.http.customer.post.dto.RequestPostCustomer;
import com.example.core.domain.customer.Customer;
import com.example.core.usecases.RegisterNewCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("courses-store/customer")
public class PostCustomerController {

    private final RegisterNewCustomer registerNewCustomer;
    private final RequestPostValidator requestPostValidator;

    public PostCustomerController(RegisterNewCustomer registerNewCustomer,
                                  RequestPostValidator requestPostValidator){
        this.registerNewCustomer = registerNewCustomer;
        this.requestPostValidator = requestPostValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PostCustomerController.class);

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> registerCustomer (@RequestBody RequestPostCustomer body) {

            requestPostValidator.valid(body);

            Customer customer = PostCustomerConverter.toDomain(body);
            customer.setIdCustomer(UUID.randomUUID());

            registerNewCustomer.execute(customer);
            log.info("Customer has been registered: {}", customer);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
