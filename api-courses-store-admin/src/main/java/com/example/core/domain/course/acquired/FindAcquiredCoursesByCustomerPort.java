package com.example.core.domain.course.acquired;

import com.example.core.domain.course.Course;

import java.util.List;
import java.util.Map;

public interface FindAcquiredCoursesByCustomerPort {
    public List<AcquiredCourse> findAcquiredCourses(String customerId);

}
