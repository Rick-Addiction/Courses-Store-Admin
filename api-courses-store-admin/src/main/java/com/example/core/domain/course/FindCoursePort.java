package com.example.core.domain.course;

import java.util.List;
import java.util.Map;

public interface FindCoursePort {
    public List<Course> findCourse(Map<String,String> courseSearchValues);

}
