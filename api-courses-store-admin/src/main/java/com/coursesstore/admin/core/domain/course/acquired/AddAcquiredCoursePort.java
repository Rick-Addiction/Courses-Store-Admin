package com.coursesstore.admin.core.domain.course.acquired;

import com.coursesstore.admin.core.domain.customer.Customer;

public interface AddAcquiredCoursePort {

    void addNewAcquiredCourseByCustomer(Customer customer);

}
