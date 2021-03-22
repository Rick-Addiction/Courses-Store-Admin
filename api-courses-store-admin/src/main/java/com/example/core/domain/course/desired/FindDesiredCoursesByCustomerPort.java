package com.example.core.domain.course.desired;

import java.util.List;
import java.util.Map;

public interface FindDesiredCoursesByCustomerPort {
    public List<DesiredCourse> findDesiredCourse(String customerId);

}
