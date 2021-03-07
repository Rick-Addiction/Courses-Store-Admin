package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.UpdateCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomer implements UpdateCustomerPort {

    private final CustomerRepository customerRepository;

    public UpdateCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void updateCustomer(Customer customer){

        CustomerModel customerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer())).get();

        if(customerModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        customerModel = CustomerConverter.toModel(customer);
        customerModel.setIdCustomer(String.valueOf(customer.getIdCustomer()));

        customerRepository.save(customerModel);


    }

}
