package com.example.core.usecases;

import com.example.core.domain.course.Course;
import com.example.core.domain.course.FindCoursePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SearchForCourse {

    private final FindCoursePort findCoursePort;

    public SearchForCourse(FindCoursePort findCoursePort){
        this.findCoursePort=findCoursePort;
    }

    private static final Logger log = LoggerFactory.getLogger(SearchForCustomer.class);

    public List<Course> execute(Map<String,String> courseSearchValues){

        return findCoursePort.findCourse(courseSearchValues);


    }



}
