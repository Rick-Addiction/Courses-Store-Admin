package com.example.core.usecases;

import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.domain.course.desired.UpdateDesiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseDesiredByCustomer {

    private final UpdateDesiredCoursePort updateDesiredCoursePort;

    public UpdateCourseDesiredByCustomer(UpdateDesiredCoursePort updateDesiredCoursePort){
        this.updateDesiredCoursePort=updateDesiredCoursePort;
    }

    public void execute(DesiredCourse course) {
        updateDesiredCoursePort.updateDesiredCourse(course);
    }

}
