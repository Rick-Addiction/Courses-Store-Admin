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
public class DeleteCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Delete a customer in the database if the ID exists")
    public void Delete_a_customer_in_the_database_if_the_ID_exists () {

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstName("Bruno");
        customer.setLastName("Salgado");
        customer.setDocType("1");
        customer.setDocNumber("987654321");

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> optionalCreatedCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(optionalCreatedCustomerModel.isPresent());

        Customer customerToBeDeleted = new Customer();
        customerToBeDeleted.setIdCustomer(customer.getIdCustomer());

        DeleteCustomer deleteCustomer = new DeleteCustomer(customerRepository);
        deleteCustomer.deleteCustomer(customerToBeDeleted);

        Optional<CustomerModel> optionalDeletedCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(optionalDeletedCustomerModel.isEmpty());

    }
}
