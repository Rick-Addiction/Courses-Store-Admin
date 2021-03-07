package com.example.core.usecases;


import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.DeleteCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCustomer {

    private final DeleteCustomerPort deleteCustomerPort;

    public ExcludeCustomer(DeleteCustomerPort deleteCustomerPort){ this.deleteCustomerPort=deleteCustomerPort; }

    public void execute(Customer customer) { deleteCustomerPort.deleteCustomer(customer); }

}
