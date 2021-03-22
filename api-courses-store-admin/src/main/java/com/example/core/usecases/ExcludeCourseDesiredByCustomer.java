package com.example.core.usecases;

import com.example.core.domain.course.desired.DeleteDesiredCoursePort;
import com.example.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCourseDesiredByCustomer {

    private final DeleteDesiredCoursePort deleteDesiredCoursePort;

    public ExcludeCourseDesiredByCustomer(DeleteDesiredCoursePort deleteDesiredCoursePort) {
        this.deleteDesiredCoursePort = deleteDesiredCoursePort;
    }

    public void execute(DesiredCourse course) {
        deleteDesiredCoursePort.deleteDesiredCourse(course);
    }

}
