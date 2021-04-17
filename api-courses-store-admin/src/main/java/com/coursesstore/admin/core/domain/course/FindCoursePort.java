package com.coursesstore.admin.core.domain.course;

import java.util.List;
import java.util.Map;

public interface FindCoursePort {
    public List<Course>  findCourse(String courseSearchValues);

}
