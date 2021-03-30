package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.UpdateCustomerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UpdateCustomerRegistrationTest {

    @Mock
    private UpdateCustomerPort updateCustomerPort;

    @Test
    @DisplayName("Given a Customer domain object, When this customer is updated, Then it should be done successfully")
    public void Given_a_Customer_domain_object_When_this_customer_is_updated_Then_it_should_be_done_successfully(){

        ///Arrange
        UpdateCustomerRegistration updateCustomerRegistration = new UpdateCustomerRegistration(updateCustomerPort);

        ///Act
        updateCustomerRegistration.execute(new Customer());

        ///Assert
        verify(updateCustomerPort, times(1)).updateCustomer(any(Customer.class));
    }
}