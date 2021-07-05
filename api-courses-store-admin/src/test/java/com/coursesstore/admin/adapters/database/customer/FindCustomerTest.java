package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.course.FindCourse;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class FindCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database, When its searched for this Customer, Then return the Customer searched")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_When_its_searched_for_this_Customer_Then_return_the_Customer_searched() {

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        ///Act
        FindCustomer findCustomer = new FindCustomer(customerRepository);

        var customersList = findCustomer.findCustomer(
                String.valueOf(customer.getIdCustomer())
        );

        ///Assert
        assertNotNull(customersList);

    }

    @Test
    @DisplayName("Given an invalid Customer domain, When it is tried to find this Customer, Then it will throw a DataNotFoundException")
    void Given_an_invalid_Customer_domain_When_it_is_tried_to_find_this_Customer_Then_it_will_throw_a_DataNotFoundException() {
        ///Arrange
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        ///Act
        FindCustomerById findCustomerById = new FindCustomerById(customerRepository);

        ///Assert
        DataNotFoundException exception = assertThrows(
                DataNotFoundException.class,
                () -> findCustomerById.findCustomer(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"
                )
        );

        assertEquals("Customer not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }

}
