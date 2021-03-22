package com.example.adapters.http.customer.put.dto;

import com.example.core.domain.customer.Customer;

import java.util.UUID;

public class PutCustomerConverter {

    public static Customer toDomain (RequestPutCustomer body) {
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(body.getIdCustomer()));
        customer.setFirstname(body.getFirstname());
        customer.setLastname(body.getFirstname());
        customer.setPhone(body.getPhone());
        customer.setEmail(body.getEmail());
        customer.setCompany(body.getCompany());
        customer.setPosition(body.getPosition());

        return customer;
    }
}
