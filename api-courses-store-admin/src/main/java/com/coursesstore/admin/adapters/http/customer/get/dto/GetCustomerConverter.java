package com.coursesstore.admin.adapters.http.customer.get.dto;

import com.coursesstore.admin.core.domain.customer.Customer;

public class GetCustomerConverter {

    public static ResponseGetCustomer toResponseGetCustomer (Customer customer) {
        ResponseGetCustomer responseGetCustomer = new ResponseGetCustomer();
        responseGetCustomer.setIdCustomer(String.valueOf(customer.getIdCustomer()));
        responseGetCustomer.setFirstname(customer.getFirstname());
        responseGetCustomer.setLastname(customer.getLastname());
        responseGetCustomer.setPhone(customer.getPhone());
        responseGetCustomer.setEmail(customer.getEmail());
        responseGetCustomer.setCompany(customer.getCompany());
        responseGetCustomer.setPosition(customer.getPosition());
        responseGetCustomer.setLinkedIn(customer.getLinkedIn());

        return responseGetCustomer;
    }
}
