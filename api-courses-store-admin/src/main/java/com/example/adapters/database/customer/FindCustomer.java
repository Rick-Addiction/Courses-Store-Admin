package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.FindCustomerPort;
import com.example.exceptions.DataNotFoundException;
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
            customer.setFirstName(c.getFirstName());
            customer.setLastName(c.getLastName());
            customer.setDocType(c.getDocType());
            customer.setDocNumber(c.getDocNumber());

            listCustomers.add(customer);
        }

        return listCustomers;
    }
}
