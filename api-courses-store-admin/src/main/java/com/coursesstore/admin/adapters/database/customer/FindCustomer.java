package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FindCustomer implements FindCustomerPort {

    private final CustomerRepository customerRepository;

    public FindCustomer(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    public List<Customer> findCustomer(String customerSearchValues) {
        Iterable<CustomerModel> listCustomersModel;

        listCustomersModel = customerRepository.findAll();

        List<Customer> listCustomers = new ArrayList<>();

        for (CustomerModel c : listCustomersModel) {
            var customer = new Customer();
            customer.setIdCustomer(UUID.fromString(c.getIdCustomer()));
            customer.setFirstname(c.getFirstname());
            customer.setLastname(c.getLastname());
            customer.setPhone(c.getPhone());
            customer.setEmail(c.getEmail());
            customer.setCompany(c.getCompany());
            customer.setPosition(c.getPosition());
            customer.setLinkedIn(c.getLinkedIn());

            listCustomers.add(customer);
        }

        return listCustomers;
    }
}
