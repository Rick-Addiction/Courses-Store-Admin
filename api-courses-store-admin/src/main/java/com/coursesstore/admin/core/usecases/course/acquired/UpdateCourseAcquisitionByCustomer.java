package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.acquired.UpdateAcquiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseAcquisitionByCustomer {

    private final UpdateAcquiredCoursePort updateAcquiredCoursePort;

    public UpdateCourseAcquisitionByCustomer(UpdateAcquiredCoursePort updateAcquiredCoursePort){
        this.updateAcquiredCoursePort=updateAcquiredCoursePort;
    }

    public void execute(Customer customer) {
        updateAcquiredCoursePort.updateAcquiredCourse(customer);
    }

}
