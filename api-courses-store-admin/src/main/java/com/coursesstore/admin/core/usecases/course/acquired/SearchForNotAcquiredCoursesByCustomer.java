package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.FindNotAcquiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForNotAcquiredCoursesByCustomer {

    private final FindNotAcquiredCoursesByCustomerPort findNotAcquiredCoursesByCustomerPort;

    public SearchForNotAcquiredCoursesByCustomer(FindNotAcquiredCoursesByCustomerPort findNotAcquiredCoursesByCustomerPort){
        this.findNotAcquiredCoursesByCustomerPort = findNotAcquiredCoursesByCustomerPort;
    }

    public List<Course> execute(String customerId){

        return findNotAcquiredCoursesByCustomerPort.findNotAcquiredCourses(customerId);
    }



}
