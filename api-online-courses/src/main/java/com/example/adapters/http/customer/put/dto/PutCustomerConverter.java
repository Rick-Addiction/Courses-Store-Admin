package com.example.adapters.http.customer.put.dto;

import com.example.core.domain.customer.Customer;

public class PutCustomerConverter {

    public static Customer toDomain (RequestPutCustomer body) {
        Customer customer = new Customer();
        customer.setIdCustomer(body.getId());
        customer.setFirstName(body.getFirstName());
        customer.setLastName(body.getLastName());
        customer.setDocType(body.getDocType());
        customer.setDocNumber(body.getDocNumber());

        return customer;
    }
}
