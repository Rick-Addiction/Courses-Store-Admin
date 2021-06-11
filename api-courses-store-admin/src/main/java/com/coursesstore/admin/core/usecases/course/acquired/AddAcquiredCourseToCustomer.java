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

    public AddAcquiredCourseToCustomer(AddAcquiredCoursePort addAcquiredCourse){
        this.addAcquiredCourse=addAcquiredCourse;
    }

    public void execute(Customer customer) {

        addAcquiredCourse.addNewAcquiredCourseByCustomer(customer);
    }

}
