package com.example.adapters.database.customer;

import com.example.adapters.database.customer.model.CustomerModel;
import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.FindCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FindCustomer implements FindCustomerPort {

    private final CustomerRepository customerRepository;

    public FindCustomer(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }


    private static final Logger log = LoggerFactory.getLogger(FindCustomer.class);

    @Override
    public List<Customer> findCustomer(Map<String, String> customerSearchValues) {
        Iterable<CustomerModel> listCustomersModel;

        listCustomersModel = customerRepository.findAll();

        List<Customer> listCustomers = new ArrayList<>();

        for (CustomerModel c : listCustomersModel) {
            Customer customer = new Customer();
            customer.setIdCustomer(UUID.fromString(c.getIdCustomer()));
            customer.setFirstname(c.getFirstname());
            customer.setLastname(c.getFirstname());
            customer.setPhone(c.getPhone());
            customer.setEmail(c.getEmail());
            customer.setCompany(c.getCompany());
            customer.setPosition(c.getPosition());

            listCustomers.add(customer);
        }

        return listCustomers;
    }
}
