package com.coursesstore.admin.adapters.database.customer;

import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
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
public class CreateCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Given a valid Customer domain object, When the customer is not in the database, Then create a new customer")
    public void Given_a_valid_Customer_domain_object_When_the_customer_is_not_in_the_database_Then_create_a_new_customer(){

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();

        ///Act
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        ///Assert
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