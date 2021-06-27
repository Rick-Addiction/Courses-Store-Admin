package com.coursesstore.admin.adapters.database.customer.model;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class CustomerConverterTest {

    @Test
    @DisplayName("Given a valid Customer entity, When the object is converted to a Customer model, Then It should be done successfully")
    void Given_a_valid_Customer_entity_When_the_object_is_converted_to_a_Customer_model_Then_It_should_be_done_successfully(){

        ///Arrange
        Customer customer = DomainUtils.generateCustomer();

        ///Act
        CustomerModel customerModel = CustomerConverter.toModel(customer);

        ///Assert
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
    @DisplayName("Given a valid Customer model, When the object is converted to a Customer entity, Then It should be done successfully")
    void Given_a_valid_Customer_model_When_the_object_is_converted_to_a_Customer_entity_Then_It_should_be_done_successfully(){

        ///Arrange
        CustomerModel customerModel = DomainUtils.generateCustomerModel();

        ///Act
        Customer customer = CustomerConverter.toEntity(customerModel);

        ///Assert
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
