package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.FindNotAcquiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForNotAcquiredCoursesByCustomer {

    private final FindNotAcquiredCoursesByCustomerPort findNotAcquiredCoursesByCustomerPort;

    public SearchForNotAcquiredCoursesByCustomer(FindNotAcquiredCoursesByCustomerPort findNotAcquiredCoursesByCustomerPort){
        this.findNotAcquiredCoursesByCustomerPort = findNotAcquiredCoursesByCustomerPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForNotAcquiredCoursesByCustomer.class);

    public List<Course> execute(String customerId){

        return findNotAcquiredCoursesByCustomerPort.findNotAcquiredCourses(customerId);
    }



}
