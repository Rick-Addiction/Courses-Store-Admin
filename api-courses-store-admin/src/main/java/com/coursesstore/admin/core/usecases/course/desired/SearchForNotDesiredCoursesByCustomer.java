package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.FindNotDesiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForNotDesiredCoursesByCustomer {

    private final FindNotDesiredCoursesByCustomerPort findNotDesiredCoursesByCustomerPort;

    public SearchForNotDesiredCoursesByCustomer(FindNotDesiredCoursesByCustomerPort findNotDesiredCoursesByCustomerPort){
        this.findNotDesiredCoursesByCustomerPort = findNotDesiredCoursesByCustomerPort;
    }

    public List<Course> execute(String customerId){

        return findNotDesiredCoursesByCustomerPort.findNotDesiredCourses(customerId);
    }



}
