package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FindCourse implements FindCoursePort {

    private final CourseRepository courseRepository;

    public FindCourse(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @Override
    public Course findCourse(String idCourse) {

        Optional<CourseModel> courseModelOptional = courseRepository.findByIdCourse(idCourse);

        if(courseModelOptional.isEmpty()){
            throw new ModelException("Course not found -  Course " + idCourse +"!");
        }

        var courseModel = courseModelOptional.get();

        var course = new Course();
        course.setIdCourse(UUID.fromString(courseModel.getIdCourse()));
        course.setName(courseModel.getName());
        course.setOriginalValue(courseModel.getOriginalValue());

        var teacherResponsible = new Teacher();
        teacherResponsible.setIdTeacher(UUID.fromString(courseModel.getTeacherResponsible().getIdTeacher()));
        teacherResponsible.setName(courseModel.getTeacherResponsible().getName());
        course.setTeacherResponsible(teacherResponsible);


        return course;
    }

    @Override
    public List<Course>  findCourse() {
        Iterable<CourseModel> listCoursesModel;

        listCoursesModel = courseRepository.findAll();

        List<Course> listCourses = new ArrayList<>();

        for (CourseModel c : listCoursesModel) {
            var course = new Course();
            course.setIdCourse(UUID.fromString(c.getIdCourse()));
            course.setName(c.getName());
            course.setOriginalValue(c.getOriginalValue());

            var teacherResponsible = new Teacher();
            teacherResponsible.setIdTeacher(UUID.fromString(c.getTeacherResponsible().getIdTeacher()));
            teacherResponsible.setName(c.getTeacherResponsible().getName());
            course.setTeacherResponsible(teacherResponsible);

            listCourses.add(course);
        }

        return listCourses;
    }
}
