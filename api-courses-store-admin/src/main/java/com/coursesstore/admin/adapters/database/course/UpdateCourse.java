package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.UpdateCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateCourse implements UpdateCoursePort {

    private final CourseRepository courseRepository;

    public UpdateCourse(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void updateCourse(Course course){

        var idCourse = String.valueOf(course.getIdCourse());

        Optional<CourseModel> courseModelOptional = courseRepository.findByIdCourse(idCourse);

        if(courseModelOptional.isEmpty()){
            throw new ModelException("Course not found -  Course " + idCourse +"!");
        }

        var courseModel = CourseConverter.toModel(course);
        courseModel.setIdCourse(String.valueOf(course.getIdCourse()));

        courseRepository.save(courseModel);
    }
}
