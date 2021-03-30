package com.coursesstore.admin.core.usecases.customer;


import com.coursesstore.admin.core.domain.customer.UpdateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class UpdateCustomerRegistration {

    private final UpdateCustomerPort updateCustomerPort;

    public UpdateCustomerRegistration(UpdateCustomerPort updateCustomerPort) { this.updateCustomerPort = updateCustomerPort; }

    public void execute(Customer customer) { updateCustomerPort.updateCustomer(customer); }

}
