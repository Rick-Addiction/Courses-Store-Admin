package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.UpdateCourse;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
class UpdateCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a valid Customer stored in the database, When its requested to update the Customer, Then it should be done successfully")
    void Given_a_valid_Customer_stored_in_the_database_When_its_requested_to_update_the_Customer_Then_it_should_be_done_successfully() {

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(customerModel.isPresent());

        ///Act
        Customer customerToUpdate = customer;
        customerToUpdate.setFirstname("Joel");

        UpdateCustomer updateCustomer = new UpdateCustomer(customerRepository);
        updateCustomer.updateCustomer(customerToUpdate);

        ///Assert
        Optional<CustomerModel> optionalCustomerModelUpdated = customerRepository.findByIdCustomer(String.valueOf(customerToUpdate.getIdCustomer()));

        assertTrue(optionalCustomerModelUpdated.isPresent());

        CustomerModel customerModelUpdated = optionalCustomerModelUpdated.get();
        Customer customerUpdated = CustomerConverter.toEntity(customerModelUpdated);

        assertEquals(customerUpdated.getIdCustomer(), customerToUpdate.getIdCustomer());
        assertEquals(customerUpdated.getFirstname(),customerToUpdate.getFirstname());
        assertEquals(customerUpdated.getLastname(),customerToUpdate.getLastname());
        assertEquals(customerUpdated.getPhone(),customerToUpdate.getPhone());
        assertEquals(customerUpdated.getEmail(),customerToUpdate.getEmail());
        assertEquals(customerUpdated.getLinkedIn(),customerToUpdate.getLinkedIn());
        assertEquals(customerUpdated.getCompany(),customerToUpdate.getCompany());
        assertEquals(customerUpdated.getPosition(),customerToUpdate.getPosition());

    }

    @Test
    @DisplayName("Given an invalid Customer domain, When it is tried to update this Customer, Then it will throw a ModelException")
    void Given_an_invalid_Customer_domain_When_it_is_tried_to_update_this_Customer_Then_it_will_throw_a_ModelException() {
        ///Arrange
        Customer customer = DomainUtils.generateCustomer();
        customer.setIdCustomer(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        ///Act
        UpdateCustomer updateCustomer = new UpdateCustomer(customerRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> updateCustomer.updateCustomer(
                        customer
                )
        );

        assertEquals("Customer not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
}
