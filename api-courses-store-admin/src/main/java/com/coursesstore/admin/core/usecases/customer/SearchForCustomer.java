package com.coursesstore.admin.core.usecases.customer;

import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.customer.FindCustomerByIdPort;
import com.coursesstore.admin.core.domain.customer.FindCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForCustomer {

    private final FindCustomerByIdPort findCustomerByIdPort;

    public SearchForCustomer(FindCustomerByIdPort findCustomerByIdPort){
        this.findCustomerByIdPort=findCustomerByIdPort;
    }

    public Customer execute(String idCustomer){
        return findCustomerByIdPort.findCustomer(idCustomer);
    }


}
