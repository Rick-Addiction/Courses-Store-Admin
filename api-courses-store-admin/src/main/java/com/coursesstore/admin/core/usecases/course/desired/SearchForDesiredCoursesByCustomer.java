package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForDesiredCoursesByCustomer {

    private final FindDesiredCoursesByCustomerPort findDesiredCoursesByCustomerPort;

    public SearchForDesiredCoursesByCustomer(FindDesiredCoursesByCustomerPort findDesiredCoursesByCustomerPort){
        this.findDesiredCoursesByCustomerPort = findDesiredCoursesByCustomerPort;
    }

    public List<DesiredCourse> execute(String customerId){

        return findDesiredCoursesByCustomerPort.findDesiredCourses(customerId);
    }



}
