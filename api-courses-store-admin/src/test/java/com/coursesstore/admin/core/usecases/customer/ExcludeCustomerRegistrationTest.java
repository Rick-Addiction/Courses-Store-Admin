package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.DeleteCustomerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ExcludeCustomerRegistrationTest {

    @Mock
    private DeleteCustomerPort deleteCustomerPort;

    @Test
    @DisplayName("Given a Customer domain object, When this customer is excluded, Then it should be done successfully")
    void Given_a_Customer_domain_object_When_this_customer_is_excluded_Then_it_should_be_done_successfully(){

        ///Arrange
        ExcludeCustomerRegistration excludeCustomerRegistration = new ExcludeCustomerRegistration(deleteCustomerPort);

        ///Act
        excludeCustomerRegistration.execute("id_customer");

        ///Assert
        verify(deleteCustomerPort, times(1)).deleteCustomer(any(String.class));
    }
}