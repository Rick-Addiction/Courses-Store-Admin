package com.example.core.usecases;


import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.DeleteAcquiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCourseAcquisitionByCustomer {

    private final DeleteAcquiredCoursePort deleteAcquiredCoursePort;

    public ExcludeCourseAcquisitionByCustomer(DeleteAcquiredCoursePort deleteAcquiredCoursePort) {
        this.deleteAcquiredCoursePort = deleteAcquiredCoursePort;
    }

    public void execute(AcquiredCourse course) {
        deleteAcquiredCoursePort.deleteAcquiredCourse(course);
    }

}
