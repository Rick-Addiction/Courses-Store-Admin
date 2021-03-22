package com.example.core.usecases;

import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.AddAcquiredCoursePort;
import com.example.core.domain.course.acquired.UpdateAcquiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseAcquisitionByCustomer {

    private final UpdateAcquiredCoursePort updateAcquiredCoursePort;

    public UpdateCourseAcquisitionByCustomer(UpdateAcquiredCoursePort updateAcquiredCoursePort){
        this.updateAcquiredCoursePort=updateAcquiredCoursePort;
    }

    public void execute(AcquiredCourse course) {
        updateAcquiredCoursePort.updateAcquiredCourse(course);
    }

}
