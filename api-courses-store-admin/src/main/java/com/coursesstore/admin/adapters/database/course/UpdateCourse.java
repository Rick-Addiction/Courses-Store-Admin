package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.UpdateCoursePort;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourse implements UpdateCoursePort {

    private final CourseRepository courseRepository;

    public UpdateCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void updateCourse(Course course){

        CourseModel courseModel = courseRepository.findByIdCourse(String.valueOf(course.getIdCourse())).get();

        if(courseModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        courseModel = CourseConverter.toModel(course);
        courseModel.setIdCourse(String.valueOf(course.getIdCourse()));

        courseRepository.save(courseModel);
    }
}
