package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.UpdateCustomerPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateCustomer implements UpdateCustomerPort {

    private final CustomerRepository customerRepository;

    public UpdateCustomer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void updateCustomer(Customer customer){

        var idCustomer = String.valueOf(customer.getIdCustomer());

        Optional<CustomerModel> customerModelOptional = customerRepository.findByIdCustomer(idCustomer);

        if(customerModelOptional.isEmpty()){
            throw new ModelException("Customer not found -  Customer " + idCustomer +"!");
        }

        var customerModel = CustomerConverter.toModel(customer);
        customerModel.setIdCustomer(String.valueOf(customer.getIdCustomer()));

        customerRepository.save(customerModel);


    }

}
