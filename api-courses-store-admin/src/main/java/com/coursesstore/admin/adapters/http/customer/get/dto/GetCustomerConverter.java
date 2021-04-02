package com.coursesstore.admin.adapters.http.customer.get.dto;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class GetCustomerConverter {

    public static ResponseGetCustomer toResponseGetCustomer (List<Customer> listCustomers) {
        ResponseGetCustomer responseGetCustomer = new ResponseGetCustomer();

        if(!listCustomers.isEmpty()) {

            responseGetCustomer.setCustomers(new ArrayList<>());

            for (Customer c : listCustomers) {
                ResponseGetCustomer.Customer customer = new ResponseGetCustomer.Customer();
                customer.setIdCustomer(String.valueOf(c.getIdCustomer()));
                customer.setFirstname(c.getFirstname());
                customer.setLastname(c.getLastname());
                customer.setPhone(c.getPhone());
                customer.setEmail(c.getEmail());
                customer.setCompany(c.getCompany());
                customer.setPosition(c.getPosition());
                customer.setLinkedIn(c.getLinkedIn());

                responseGetCustomer.getCustomers().add(customer);
            }
        }

        return responseGetCustomer;
    }
}
