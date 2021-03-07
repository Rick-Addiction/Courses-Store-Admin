package com.example.adapters.http.customer.post.dto;

import com.example.core.domain.customer.Customer;

public class PostCustomerConverter {

    public static Customer toDomain (RequestPostCustomer body){
        Customer customer = new Customer();
        customer.setFirstName(body.getFirstName());
        customer.setLastName(body.getLastName());
        customer.setDocType(body.getDocType());
        customer.setDocNumber(body.getDocNumber());

        return customer;
    }

}
