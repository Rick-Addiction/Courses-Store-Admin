package com.example.adapters.http.customer.post.dto;

import com.example.core.domain.customer.Customer;

public class PostCustomerConverter {

    public static Customer toDomain (RequestPostCustomer body){
        Customer customer = new Customer();
        customer.setFirstname(body.getFirstname());
        customer.setLastname(body.getFirstname());
        customer.setPhone(body.getPhone());
        customer.setEmail(body.getEmail());
        customer.setCompany(body.getCompany());
        customer.setPosition(body.getPosition());

        return customer;
    }

}
