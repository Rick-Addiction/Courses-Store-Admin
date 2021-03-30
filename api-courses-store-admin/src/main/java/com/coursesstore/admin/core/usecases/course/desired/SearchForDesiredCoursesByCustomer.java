package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForDesiredCoursesByCustomer {

    private final FindDesiredCoursesByCustomerPort findDesiredCoursesByCustomerPort;

    public SearchForDesiredCoursesByCustomer(FindDesiredCoursesByCustomerPort findDesiredCoursesByCustomerPort){
        this.findDesiredCoursesByCustomerPort = findDesiredCoursesByCustomerPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForDesiredCoursesByCustomer.class);

    public List<DesiredCourse> execute(String customerId){

        return findDesiredCoursesByCustomerPort.findDesiredCourse(customerId);
    }



}
