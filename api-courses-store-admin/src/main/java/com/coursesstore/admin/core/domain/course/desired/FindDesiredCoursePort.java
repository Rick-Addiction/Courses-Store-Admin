package com.coursesstore.admin.core.domain.course.desired;

public interface FindDesiredCoursePort {

    public DesiredCourse findDesiredCourse(String idCustomer, String idCourse);
}
