package com.example.adapters.database.customer;

import com.example.core.domain.customer.CreateCustomerPort;
import com.example.core.domain.customer.Customer;
import com.example.exceptions.CustomerConflictException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CreateCustomer implements CreateCustomerPort {

    private final CustomerRepository customerRepository;

    public CreateCustomer(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public void createCustomer(Customer customer) {
        try {
            CustomerModel customerModel = CustomerConverter.toModel(customer);
            customerRepository.save(customerModel);
        } catch (Exception ex) {
            throw new CustomerConflictException();
        }
    }
}
