package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.UpdateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
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
