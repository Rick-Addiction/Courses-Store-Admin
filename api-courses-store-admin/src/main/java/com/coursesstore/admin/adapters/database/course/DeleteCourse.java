package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.DeleteCoursePort;
import org.springframework.stereotype.Component;

@Component
public class DeleteCourse implements DeleteCoursePort {

    private final CourseRepository courseRepository;

    public DeleteCourse(CourseRepository courseRepository) { this.courseRepository = courseRepository; }

    @Override
    public void deleteCourse(Course course) {

        CourseModel courseToDelete = null;

        if (course.getIdCourse() != null)
            courseToDelete = courseRepository.findByIdCourse(course.getIdCourse().toString()).get();

        courseRepository.delete(courseToDelete);
    }
}
