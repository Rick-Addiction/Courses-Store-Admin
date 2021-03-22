package com.example.core.usecases;

import com.example.core.domain.course.Course;
import com.example.core.domain.course.CreateCoursePort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterNewCourse {

    private final CreateCoursePort createCoursePort;

    public RegisterNewCourse(CreateCoursePort createCoursePort){
        this.createCoursePort=createCoursePort;
    }

    public void execute(Course course) {
        course.setIdCourse(UUID.randomUUID());
        createCoursePort.createCourse(course);
    }

}
