package com.example.core.usecases;

import com.example.core.domain.customer.CreateCustomerPort;
import com.example.core.domain.customer.Customer;
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
    @DisplayName("When a new customer is registered, So a new customer record is created")
    public void When_a_new_customer_is_registered_So_a_new_customer_record_is_created(){

        RegisterNewCustomer registerNewCustomer = new RegisterNewCustomer(createCustomerPort);
        registerNewCustomer.execute(new Customer());

        verify(createCustomerPort, times(1)).createCustomer(any(Customer.class));
    }


}