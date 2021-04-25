package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.DeleteCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteCustomer implements DeleteCustomerPort {

    private final CustomerRepository customerRepository;

    public DeleteCustomer(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    @Override
    public void deleteCustomer(String idCustomer) {

        Optional<CustomerModel> customerToDelete = customerRepository.findByIdCustomer(idCustomer);

        customerRepository.delete(customerToDelete.get());
    }
}
