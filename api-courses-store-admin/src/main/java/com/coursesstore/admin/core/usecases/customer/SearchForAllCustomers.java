package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForAllCustomers {

    private final FindCustomerPort findCustomerPort;

    public SearchForAllCustomers(FindCustomerPort findCustomerPort){
        this.findCustomerPort=findCustomerPort;
    }

    public List<Customer> execute(String customerSearchValues){

        return findCustomerPort.findCustomer(customerSearchValues);


    }


}
