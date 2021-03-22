package com.example.core.usecases;

import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.FindAcquiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForAcquiredCoursesByCustomer {

    private final FindAcquiredCoursesByCustomerPort findAcquiredCoursesByCustomerPort;

    public SearchForAcquiredCoursesByCustomer(FindAcquiredCoursesByCustomerPort findAcquiredCoursesByCustomerPort){
        this.findAcquiredCoursesByCustomerPort = findAcquiredCoursesByCustomerPort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForAcquiredCoursesByCustomer.class);

    public List<AcquiredCourse> execute(String customerId){

        return findAcquiredCoursesByCustomerPort.findAcquiredCourses(customerId);
    }



}
