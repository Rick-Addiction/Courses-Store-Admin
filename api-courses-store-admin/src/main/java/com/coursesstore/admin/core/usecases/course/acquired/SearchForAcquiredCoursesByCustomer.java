package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForAcquiredCoursesByCustomer {

    private final FindAcquiredCoursesByCustomerPort findAcquiredCoursesByCustomerPort;

    public SearchForAcquiredCoursesByCustomer(FindAcquiredCoursesByCustomerPort findAcquiredCoursesByCustomerPort){
        this.findAcquiredCoursesByCustomerPort = findAcquiredCoursesByCustomerPort;
    }

    public List<AcquiredCourse> execute(String customerId){

        return findAcquiredCoursesByCustomerPort.findAcquiredCourses(customerId);
    }



}
