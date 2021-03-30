package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class FindCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database, When its searched for this Customer, Then return the Customer searched")
    public void Given_a_valid_id_of_an_Customer_stored_in_the_database_When_its_searched_for_this_Customer_Then_return_the_Customer_searched() {

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        ///Act
        FindCustomer findCustomer = new FindCustomer(customerRepository);

        ///Assert
        //TODO Set verify

    }

}