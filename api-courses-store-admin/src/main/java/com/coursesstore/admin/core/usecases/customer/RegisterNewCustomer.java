package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.CreateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterNewCustomer {

    private final CreateCustomerPort createCustomerPort;

    public RegisterNewCustomer(CreateCustomerPort createCustomerPort){
        this.createCustomerPort=createCustomerPort;
    }

    public void execute(Customer customer) {
        customer.setIdCustomer(UUID.randomUUID());
        createCustomerPort.createCustomer(customer);
    }

}
