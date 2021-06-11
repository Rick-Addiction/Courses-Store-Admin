package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.DeleteCoursePort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCourseRegistration {

    private final DeleteCoursePort deleteCoursePort;

    public ExcludeCourseRegistration(DeleteCoursePort deleteCoursePort){ this.deleteCoursePort=deleteCoursePort; }

    public void execute(String idCourse) { deleteCoursePort.deleteCourse(idCourse); }

}
