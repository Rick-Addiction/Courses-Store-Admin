package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class CustomerConverterTest {

    @Test
    @DisplayName("Customer entity to model")
    public void Customer_entity_to_model(){

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstname("Samanta");
        customer.setLastname("Laster");
        customer.setPhone("+55 11 99999-9999");
        customer.setEmail("email_test@testdomain.com");
        customer.setCompany("Robots with Love");
        customer.setPosition("CEO");

        CustomerModel customerModel = CustomerConverter.toModel(customer);

        assertEquals(customerModel.getIdCustomer(), String.valueOf(customer.getIdCustomer()));
        assertEquals(customerModel.getFirstname(),customer.getFirstname());
        assertEquals(customerModel.getLastname(),customer.getLastname());
        assertEquals(customerModel.getPhone(),customer.getPhone());
        assertEquals(customerModel.getEmail(),customer.getEmail());
        assertEquals(customerModel.getLinkedIn(),customer.getLinkedIn());
        assertEquals(customerModel.getCompany(),customer.getCompany());
        assertEquals(customerModel.getPosition(),customer.getPosition());


    }

    @Test
    @DisplayName("Customer model to entity")
    public void Customer_model_to_entity(){

        CustomerModel customerModel = new CustomerModel();
        customerModel.setIdCustomer(String.valueOf(UUID.randomUUID()));
        customerModel.setFirstname("Samanta");
        customerModel.setLastname("Laster");
        customerModel.setPhone("+55 11 99999-9999");
        customerModel.setEmail("email_test@testdomain.com");
        customerModel.setCompany("Robots with Love");
        customerModel.setPosition("CEO");

        Customer customer = CustomerConverter.toEntity(customerModel);

        assertEquals(String.valueOf(customer.getIdCustomer()), customerModel.getIdCustomer());
        assertEquals(customer.getFirstname(),customerModel.getFirstname());
        assertEquals(customer.getLastname(),customerModel.getLastname());
        assertEquals(customer.getPhone(),customerModel.getPhone());
        assertEquals(customer.getEmail(),customerModel.getEmail());
        assertEquals(customer.getLinkedIn(),customerModel.getLinkedIn());
        assertEquals(customer.getCompany(),customerModel.getCompany());
        assertEquals(customer.getPosition(),customerModel.getPosition());

    }

}
