package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddDesiredCourseToCustomer {

    private final AddDesiredCoursePort addDesiredCoursePort;

    public AddDesiredCourseToCustomer(AddDesiredCoursePort addDesiredCoursePort){
        this.addDesiredCoursePort=addDesiredCoursePort;
    }

    public void execute(Customer customer) {

        addDesiredCoursePort.addNewDesiredCourseByCustomer(customer);
    }

}
