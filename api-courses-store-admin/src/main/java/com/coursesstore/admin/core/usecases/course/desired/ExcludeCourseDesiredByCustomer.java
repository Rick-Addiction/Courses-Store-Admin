package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class ExcludeCourseDesiredByCustomer {

    private final DeleteDesiredCoursePort deleteDesiredCoursePort;

    public ExcludeCourseDesiredByCustomer(DeleteDesiredCoursePort deleteDesiredCoursePort) {
        this.deleteDesiredCoursePort = deleteDesiredCoursePort;
    }

    public void execute(String idCustomer,String idCourse) {
        deleteDesiredCoursePort.deleteDesiredCourse(idCustomer, idCourse);
    }

}
