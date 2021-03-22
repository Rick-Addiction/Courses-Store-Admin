package com.example.core.usecases;

import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.AddAcquiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class AddAcquiredCourseToCustomer {

    private final AddAcquiredCoursePort addAcquiredCourse;

    public AddAcquiredCourseToCustomer(AddAcquiredCoursePort addAcquiredCourse){
        this.addAcquiredCourse=addAcquiredCourse;
    }

    public void execute(AcquiredCourse course) {

        addAcquiredCourse.addCourse(course);
    }

}
