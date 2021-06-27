package com.coursesstore.admin.core.usecases.course;


import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.UpdateCoursePort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseRegistration {

    private final UpdateCoursePort updateCoursePort;

    public UpdateCourseRegistration(UpdateCoursePort updateCoursePort) { this.updateCoursePort = updateCoursePort; }

    public void execute(Course course) { updateCoursePort.updateCourse(course); }

}
