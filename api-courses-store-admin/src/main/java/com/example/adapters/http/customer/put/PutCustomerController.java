package com.example.adapters.http.customer.put;

import com.example.adapters.http.customer.put.dto.PutCustomerConverter;
import com.example.adapters.http.customer.put.dto.RequestPutCustomer;
import com.example.core.domain.customer.Customer;
import com.example.core.usecases.UpdateCustomerRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses-store/customer")
public class PutCustomerController {

    private final UpdateCustomerRegistration updateCustomerRegistration;
    private final RequestPutCustomerValidator requestPutCustomerValidator;


    public PutCustomerController(UpdateCustomerRegistration updateCustomerRegistration, RequestPutCustomerValidator requestPutCustomerValidator) {
        this.updateCustomerRegistration = updateCustomerRegistration;
        this.requestPutCustomerValidator = requestPutCustomerValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCustomerController.class);

    @PutMapping("/update")
    public ResponseEntity<Object> updateCustomer (@RequestBody RequestPutCustomer body) {

        requestPutCustomerValidator.valid(body);

        Customer customer = PutCustomerConverter.toDomain(body);

        updateCustomerRegistration.execute(customer);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }
}
