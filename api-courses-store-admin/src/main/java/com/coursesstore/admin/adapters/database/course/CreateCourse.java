package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.course.exception.CourseConflictException;
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
            throw new CourseConflictException();
        }
    }
}
