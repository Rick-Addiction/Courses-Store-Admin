package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.FindTeacherPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AddAcquiredCourseToCustomer {

    private final AddAcquiredCoursePort addAcquiredCourse;
    private final FindCoursePort findCoursePort;

    public AddAcquiredCourseToCustomer(AddAcquiredCoursePort addAcquiredCourse,
                                       FindCoursePort findCoursePort){
        this.addAcquiredCourse=addAcquiredCourse;
        this.findCoursePort=findCoursePort;
    }

    public void execute(Customer customer) {

        Course course = findCoursePort.findCourse(String.valueOf(customer.getAcquiredCourses().iterator().next().getCourse().getIdCourse())).get(0);
        customer.getAcquiredCourses().iterator().next().setCourse(course);
        customer.getAcquiredCourses().iterator().next().setIdAcquiredCourse(UUID.randomUUID());

        addAcquiredCourse.addNewAcquiredCourseByCustomer(customer);
    }

}
