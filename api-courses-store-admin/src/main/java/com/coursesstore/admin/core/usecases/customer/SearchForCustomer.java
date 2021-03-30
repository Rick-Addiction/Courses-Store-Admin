package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SearchForCustomer {

    private final FindCustomerPort findCustomerPort;

    public SearchForCustomer(FindCustomerPort findCustomerPort){
        this.findCustomerPort=findCustomerPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForCustomer.class);

    public List<Customer> execute(String customerSearchValues){

        return findCustomerPort.findCustomer(customerSearchValues);


    }


}
