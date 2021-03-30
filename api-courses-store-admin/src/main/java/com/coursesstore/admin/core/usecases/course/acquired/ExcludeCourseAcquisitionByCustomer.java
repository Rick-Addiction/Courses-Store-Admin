package com.coursesstore.admin.core.usecases.course.acquired;


import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
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
