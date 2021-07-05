package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.DeleteCourse;
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
class DeleteCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a Customer which is stored in the database, When its requested to delete the Customer, Then it should be done successfully")
    void Given_a_Customer_stored_in_the_database_When_its_requested_to_delete_the_Customer_Then_it_should_be_done_successfully () {

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
        deleteCustomer.deleteCustomer(String.valueOf(customerToBeDeleted.getIdCustomer()));

        ///Assert
        Optional<CustomerModel> optionalDeletedCustomerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));
        assertTrue(optionalDeletedCustomerModel.isEmpty());

    }

    @Test
    @DisplayName("Given an invalid Customer domain, When it is tried to delete this Customer, Then it will throw a ModelException")
    void Given_an_invalid_Customer_domain_When_it_is_tried_to_delete_this_Customer_Then_it_will_throw_a_ModelException() {
        ///Act
        DeleteCustomer deleteCustomer = new DeleteCustomer(customerRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> deleteCustomer.deleteCustomer(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"
                )
        );

        assertEquals("Customer not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
}
