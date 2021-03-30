package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.DeleteCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomer implements DeleteCustomerPort {

    private final CustomerRepository customerRepository;

    public DeleteCustomer(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    @Override
    public void deleteCustomer(Customer customer) {

        CustomerModel customerToDelete = null;

        if (customer.getIdCustomer() != null)
            customerToDelete = customerRepository.findByIdCustomer(customer.getIdCustomer().toString()).get();

        customerRepository.delete(customerToDelete);
    }
}
