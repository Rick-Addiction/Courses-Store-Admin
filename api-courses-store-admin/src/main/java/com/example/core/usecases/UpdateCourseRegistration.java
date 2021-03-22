package com.example.core.usecases;


import com.example.core.domain.course.Course;
import com.example.core.domain.course.UpdateCoursePort;
import com.example.core.domain.customer.Customer;
import com.example.core.domain.customer.UpdateCustomerPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseRegistration {

    private final UpdateCoursePort updateCoursePort;

    public UpdateCourseRegistration(UpdateCoursePort updateCoursePort) { this.updateCoursePort = updateCoursePort; }

    public void execute(Course course) { updateCoursePort.updateCourse(course); }

}
