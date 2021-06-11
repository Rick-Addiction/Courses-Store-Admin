package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.CreateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.customer.RegisterNewCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class RegisterNewCustomerTest {

    @Mock
    private CreateCustomerPort createCustomerPort;

    @Test
    @DisplayName("Given a Customer domain object, When its requested to be registered, Then it should be done successfully")
    public void Given_a_Customer_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){
        ///Arrange
        RegisterNewCustomer registerNewCustomer = new RegisterNewCustomer(createCustomerPort);

        ///Act
        registerNewCustomer.execute(new Customer());

        ///Assert
        verify(createCustomerPort, times(1)).createCustomer(any(Customer.class));
    }


}