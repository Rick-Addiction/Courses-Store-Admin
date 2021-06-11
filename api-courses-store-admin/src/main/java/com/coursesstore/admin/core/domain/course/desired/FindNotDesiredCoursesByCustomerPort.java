package com.coursesstore.admin.core.domain.course.desired;

import com.coursesstore.admin.core.domain.course.Course;

import java.util.List;

public interface FindNotDesiredCoursesByCustomerPort {
    public List<Course> findNotDesiredCourses(String customerId);

}
