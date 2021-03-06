package com.example.core.domain.customer;

import java.util.List;
import java.util.Map;

public interface FindCustomerPort {
    public List<Customer> findCustomer(Map<String,String> customerSearchValues);

}
