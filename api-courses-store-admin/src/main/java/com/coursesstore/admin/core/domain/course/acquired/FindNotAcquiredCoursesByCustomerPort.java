package com.coursesstore.admin.core.domain.course.acquired;

import com.coursesstore.admin.core.domain.course.Course;

import java.util.List;

public interface FindNotAcquiredCoursesByCustomerPort {
    public List<Course> findNotAcquiredCourses(String customerId);

}
