package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerByIdPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindCustomerById implements FindCustomerByIdPort {

    private final CustomerRepository customerRepository;

    public FindCustomerById(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public Customer findCustomer(String idCustomer) {
        Optional<CustomerModel> customerModel = customerRepository.findById(idCustomer);

        return customerModel.map(CustomerConverter::toEntity).orElse(null);
    }
}
