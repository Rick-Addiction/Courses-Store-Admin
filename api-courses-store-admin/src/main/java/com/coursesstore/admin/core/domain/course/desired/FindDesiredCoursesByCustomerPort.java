package com.coursesstore.admin.core.domain.course.desired;

import java.util.List;

public interface FindDesiredCoursesByCustomerPort {
    public List<DesiredCourse> findDesiredCourses(String customerId);

}
