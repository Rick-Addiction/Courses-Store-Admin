package com.example.core.usecases;


import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.UpdateCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerRegistration {

    private final UpdateCustomerPort updateCustomerPort;

    public UpdateCustomerRegistration(UpdateCustomerPort updateCustomerPort) { this.updateCustomerPort = updateCustomerPort; }

    public void execute(Customer customer) { updateCustomerPort.updateCustomer(customer); }

}
