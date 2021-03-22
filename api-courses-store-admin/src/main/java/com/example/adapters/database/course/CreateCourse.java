package com.example.adapters.database.course;

import com.example.adapters.database.course.exception.CourseConflictException;
import com.example.core.domain.course.Course;
import com.example.core.domain.course.CreateCoursePort;
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
