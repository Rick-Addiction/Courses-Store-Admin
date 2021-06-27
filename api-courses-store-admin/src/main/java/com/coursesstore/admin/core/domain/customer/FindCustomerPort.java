package com.coursesstore.admin.core.domain.customer;

import java.util.List;

public interface FindCustomerPort {
    public List<Customer> findCustomer(String customerSearchValues);

}
