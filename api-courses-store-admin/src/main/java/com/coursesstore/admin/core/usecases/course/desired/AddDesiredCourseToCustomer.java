package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddDesiredCourseToCustomer {

    private final AddDesiredCoursePort addDesiredCoursePort;
    private final FindCoursePort findCoursePort;

    public AddDesiredCourseToCustomer(AddDesiredCoursePort addDesiredCoursePort,
                                      FindCoursePort findCoursePort){
        this.addDesiredCoursePort=addDesiredCoursePort;
        this.findCoursePort=findCoursePort;
    }

    public void execute(Customer customer) {
        Course course = findCoursePort.findCourse(String.valueOf(customer.getDesiredCourses().iterator().next().getCourse().getIdCourse()));
        customer.getDesiredCourses().iterator().next().setCourse(course);
        customer.getDesiredCourses().iterator().next().setIdDesiredCourse(UUID.randomUUID());



        addDesiredCoursePort.addNewDesiredCourseByCustomer(customer);
    }

}
