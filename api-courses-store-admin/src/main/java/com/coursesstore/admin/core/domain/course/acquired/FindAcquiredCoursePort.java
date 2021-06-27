package com.coursesstore.admin.core.domain.course.acquired;

public interface FindAcquiredCoursePort {

    public AcquiredCourse findAcquiredCourse(String idCustomer, String idCourse);
}
