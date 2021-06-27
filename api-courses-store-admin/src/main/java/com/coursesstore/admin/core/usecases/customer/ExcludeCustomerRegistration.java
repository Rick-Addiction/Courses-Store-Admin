package com.coursesstore.admin.core.usecases.customer;


import com.coursesstore.admin.core.domain.customer.DeleteCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCustomerRegistration {

    private final DeleteCustomerPort deleteCustomerPort;

    public ExcludeCustomerRegistration(DeleteCustomerPort deleteCustomerPort){ this.deleteCustomerPort=deleteCustomerPort; }

    public void execute(String idCustomer) { deleteCustomerPort.deleteCustomer(idCustomer); }

}
