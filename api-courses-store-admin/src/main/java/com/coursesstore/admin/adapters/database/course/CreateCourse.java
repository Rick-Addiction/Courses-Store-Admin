package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.CreateCoursePort;
import com.coursesstore.admin.adapters.database.course.exception.CourseConflictException;
import org.springframework.stereotype.Component;

@Component
public class CreateCourse implements CreateCoursePort {

    private final CourseRepository CourseRepository;

    public CreateCourse(CourseRepository CourseRepository){
        this.CourseRepository=CourseRepository;
    }

    @Override
    public void createCourse(Course course) {
        try {
            CourseModel CourseModel = CourseConverter.toModel(course);
            CourseRepository.save(CourseModel);
        } catch (Exception ex) {
            throw new CourseConflictException();
        }
    }
}
