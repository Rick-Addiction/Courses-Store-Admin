package com.example.core.usecases;

import com.example.core.domain.course.desired.AddDesiredCoursePort;
import com.example.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class AddDesiredCourseToCustomer {

    private final AddDesiredCoursePort addDesiredCoursePort;

    public AddDesiredCourseToCustomer(AddDesiredCoursePort addDesiredCoursePort){
        this.addDesiredCoursePort=addDesiredCoursePort;
    }

    public void execute(DesiredCourse course) {

        addDesiredCoursePort.addCourse(course);
    }

}
