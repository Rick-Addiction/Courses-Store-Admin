package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SearchForCustomerTest {

    @Mock
    private FindCustomerPort findCustomerPort;

    @Test
    @DisplayName("Given a Customer id, When a new customer is searched, Then it should be done successfully")
    void Given_a_Customer_id_When_a_new_customer_is_searched_Then_it_should_be_done_successfully(){

        ///Arrange
        SearchForCustomer searchForCustomer = new SearchForCustomer(findCustomerPort);

        ///Act
        searchForCustomer.execute("id_customer");

        ///Assert
        verify(findCustomerPort, times(1)).findCustomer(any(String.class));
    }
}