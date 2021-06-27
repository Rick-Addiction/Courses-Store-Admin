package com.coursesstore.admin.adapters.database.customer.model;

import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.UUID;

public class CustomerConverter {

    private CustomerConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static CustomerModel toModel(Customer customer){
        var customerModel = new CustomerModel();
        customerModel.setIdCustomer(String.valueOf(customer.getIdCustomer()))   ;
        customerModel.setFirstname(customer.getFirstname());
        customerModel.setLastname(customer.getLastname());
        customerModel.setPhone(customer.getPhone());
        customerModel.setEmail(customer.getEmail());
        customerModel.setCompany(customer.getCompany());
        customerModel.setPosition(customer.getPosition());
        customerModel.setLinkedIn(customer.getLinkedIn());

        return customerModel;
    }

    public static Customer toEntity(CustomerModel customerModel){
        var customer = new Customer();
        customer.setIdCustomer(UUID.fromString(customerModel.getIdCustomer()));
        customer.setFirstname(customerModel.getFirstname());
        customer.setLastname(customerModel.getLastname());
        customer.setPhone(customerModel.getPhone());
        customer.setEmail(customerModel.getEmail());
        customer.setCompany(customerModel.getCompany());
        customer.setLinkedIn(customerModel.getLinkedIn());
        customer.setPosition(customerModel.getPosition());

        return customer;
    }

}
