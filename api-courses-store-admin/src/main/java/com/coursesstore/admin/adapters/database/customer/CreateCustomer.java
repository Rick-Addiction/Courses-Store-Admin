package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.exceptions.CustomerConflictException;
import com.coursesstore.admin.core.domain.customer.CreateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class CreateCustomer implements CreateCustomerPort {

    private final CustomerRepository customerRepository;

    public CreateCustomer(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public String createCustomer(Customer customer) {
        try {
            CustomerModel customerModel = CustomerConverter.toModel(customer);
            customerRepository.save(customerModel);

            return customerModel.getIdCustomer();
        } catch (Exception ex) {
            throw new CustomerConflictException();
        }
    }
}
