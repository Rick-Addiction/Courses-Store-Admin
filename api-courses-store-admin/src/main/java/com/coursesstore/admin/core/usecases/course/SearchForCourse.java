package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.usecases.customer.SearchForCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForCourse {

    private final FindCoursePort findCoursePort;

    public SearchForCourse(FindCoursePort findCoursePort){
        this.findCoursePort=findCoursePort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForCustomer.class);

    public List<Course>  execute(){

        return findCoursePort.findCourse();


    }



}
