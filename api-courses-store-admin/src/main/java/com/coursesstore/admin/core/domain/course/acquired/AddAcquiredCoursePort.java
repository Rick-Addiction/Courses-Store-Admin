package com.coursesstore.admin.core.domain.course.acquired;

public interface AddAcquiredCoursePort {

    void addNewAcquiredCourseByCustomer(String idCustomer, AcquiredCourse acquiredCourse);

}
