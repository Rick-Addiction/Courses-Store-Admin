package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.CreateCoursePort;
import org.springframework.stereotype.Component;

@Component
public class CreateCourse implements CreateCoursePort {

    private final CourseRepository courseRepository;

    public CreateCourse(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @Override
    public void createCourse(Course course) {
        try {
            var courseModel = CourseConverter.toModel(course);
            courseRepository.save(courseModel);
        } catch (Exception ex) {
            throw new ModelException(String.format("Conflict at the creating of a new Course: %s",ex.getMessage()));
        }
    }
}
