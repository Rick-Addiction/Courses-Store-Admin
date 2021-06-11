package com.coursesstore.admin.core.domain.customer;

import java.util.List;
import java.util.Map;

public interface FindCustomerPort {
    public List<Customer> findCustomer(String customerSearchValues);

}
