package com.coursesstore.admin.core.domain.course;


import java.util.List;

public interface FindCoursePort {
    public Course findCourse(String idCourse);

    public List<Course> findCourse();

}
