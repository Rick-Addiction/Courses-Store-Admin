package com.coursesstore.admin.core.domain.course.acquired;

import java.util.List;

public interface FindAcquiredCoursesByCustomerPort {
    public List<AcquiredCourse> findAcquiredCourses(String customerId);

}
