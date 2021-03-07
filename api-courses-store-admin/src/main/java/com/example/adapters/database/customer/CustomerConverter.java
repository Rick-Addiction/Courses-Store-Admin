package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;

import java.util.UUID;

public class CustomerConverter {


    public static CustomerModel toModel(Customer customer){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setIdCustomer(String.valueOf(customer.getIdCustomer()));
        customerModel.setFirstName(customer.getFirstName());
        customerModel.setLastName(customer.getLastName());
        customerModel.setDocNumber(customer.getDocNumber());
        customerModel.setDocType(customer.getDocType());

        return customerModel;
    }

    public static Customer toEntity(CustomerModel customerModel){
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(customerModel.getIdCustomer()));
        customer.setFirstName(customerModel.getFirstName());
        customer.setLastName(customerModel.getLastName());
        customer.setDocNumber(customerModel.getDocNumber());
        customer.setDocType(customerModel.getDocType());

        return customer;
    }



}
