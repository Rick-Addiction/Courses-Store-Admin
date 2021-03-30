package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a Customer which is stored in the database, When its requested to delete the Customer, Then it should be done successfully")
    public void Given_a_Customer_stored_in_the_database_When_its_requested_to_delete_the_Customer_Then_it_should_be_done_successfully () {

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> optionalCreatedCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(optionalCreatedCustomerModel.isPresent());

        Customer customerToBeDeleted = new Customer();
        customerToBeDeleted.setIdCustomer(customer.getIdCustomer());

        ///Act
        DeleteCustomer deleteCustomer = new DeleteCustomer(customerRepository);
        deleteCustomer.deleteCustomer(customerToBeDeleted);

        ///Assert
        Optional<CustomerModel> optionalDeletedCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));
        assertTrue(optionalDeletedCustomerModel.isEmpty());

    }
}
