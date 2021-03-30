package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.UpdateDesiredCoursePort;
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
