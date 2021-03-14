package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class CreateCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("When the customer there is not in the database, So create a new customer")
    public void When_the_customer_there_is_not_in_the_database_So_create_new_customer(){

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstname("John");
        customer.setLastname("Laster");
        customer.setPhone("+55 11 99999-9999");
        customer.setEmail("email_test@testdomain.com");
        customer.setCompany("Robots with Love");
        customer.setPosition("CEO");

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> optionalCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(optionalCustomerModel.isPresent());

        CustomerModel customerModel = optionalCustomerModel.get();
        Customer customerCreated = CustomerConverter.toEntity(customerModel);

        assertEquals(customer.getFirstname(),customerCreated.getFirstname());
        assertEquals(customer.getLastname(),customerCreated.getLastname());
        assertEquals(customer.getPhone(),customerCreated.getPhone());
        assertEquals(customer.getEmail(),customerCreated.getEmail());
        assertEquals(customer.getLinkedIn(),customerCreated.getLinkedIn());
        assertEquals(customer.getCompany(),customerCreated.getCompany());
        assertEquals(customer.getPosition(),customerCreated.getPosition());

    }

}