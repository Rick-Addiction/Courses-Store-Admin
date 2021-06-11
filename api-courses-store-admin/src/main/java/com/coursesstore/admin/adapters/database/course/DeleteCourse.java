package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.DeleteCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteCourse implements DeleteCoursePort {

    private final CourseRepository courseRepository;

    public DeleteCourse(CourseRepository courseRepository) { this.courseRepository = courseRepository; }

    @Override
    public void deleteCourse(String idCourse) {

        Optional<CourseModel> courseToDelete = courseRepository.findByIdCourse(idCourse);

        if(courseToDelete.isEmpty()){
            throw new ModelException("Course " + idCourse + " not Found");
        }

        courseRepository.delete(courseToDelete.get());
    }
}
