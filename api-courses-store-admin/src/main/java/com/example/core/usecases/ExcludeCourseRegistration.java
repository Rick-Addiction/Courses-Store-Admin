package com.example.core.usecases;


import com.example.core.domain.course.Course;
import com.example.core.domain.course.DeleteCoursePort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCourseRegistration {

    private final DeleteCoursePort deleteCoursePort;

    public ExcludeCourseRegistration(DeleteCoursePort deleteCoursePort){ this.deleteCoursePort=deleteCoursePort; }

    public void execute(Course course) { deleteCoursePort.deleteCourse(course); }

}
