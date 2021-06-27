package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchForCourse {

    private final FindCoursePort findCoursePort;

    public SearchForCourse(FindCoursePort findCoursePort){
        this.findCoursePort=findCoursePort;
    }

    public List<Course>  execute(){

        return findCoursePort.findCourse();


    }



}
