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
        customer.setFirstName("Bruno");
        customer.setLastName("Salgado");
        customer.setDocType("1");
        customer.setDocNumber("111111111");

        CustomerModel customerModel = CustomerConverter.toModel(customer);

        assertEquals(UUID.fromString(customerModel.getIdCustomer()), customer.getIdCustomer());
        assertEquals(customerModel.getFirstName(), customer.getFirstName());
        assertEquals(customerModel.getLastName(), customer.getLastName());
        assertEquals(customerModel.getDocType(), customer.getDocType());
        assertEquals(customerModel.getDocNumber(), customer.getDocNumber());

    }

    @Test
    @DisplayName("Customer model to entity")
    public void Customer_model_to_entity(){

        CustomerModel customerModel = new CustomerModel();
        customerModel.setIdCustomer(String.valueOf(UUID.randomUUID()));
        customerModel.setFirstName("Bruno");
        customerModel.setLastName("Salgado");
        customerModel.setDocType("1");
        customerModel.setDocNumber("111111111");

        Customer customer = CustomerConverter.toEntity(customerModel);

        assertEquals(String.valueOf(customer.getIdCustomer()), customerModel.getIdCustomer());
        assertEquals(customer.getFirstName(), customerModel.getFirstName());
        assertEquals(customer.getLastName(), customerModel.getLastName());
        assertEquals(customer.getDocType(), customerModel.getDocType());
        assertEquals(customer.getDocNumber(), customerModel.getDocNumber());

    }

}
