package com.coursesstore.admin.core.domain.course.desired;

import com.coursesstore.admin.core.domain.customer.Customer;

public interface AddDesiredCoursePort {

    void addNewDesiredCourseByCustomer(Customer customer);

}
