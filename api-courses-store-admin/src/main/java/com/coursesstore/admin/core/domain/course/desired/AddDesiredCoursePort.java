package com.coursesstore.admin.core.domain.course.desired;

public interface AddDesiredCoursePort {

    void addNewDesiredCourseByCustomer(String idCustomer, DesiredCourse desiredCourse);

}
