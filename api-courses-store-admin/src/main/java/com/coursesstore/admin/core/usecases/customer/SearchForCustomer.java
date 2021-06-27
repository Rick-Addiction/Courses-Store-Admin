package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForCustomer {

    private final FindCustomerPort findCustomerPort;

    public SearchForCustomer(FindCustomerPort findCustomerPort){
        this.findCustomerPort=findCustomerPort;
    }

    public List<Customer> execute(String customerSearchValues){

        return findCustomerPort.findCustomer(customerSearchValues);


    }


}
