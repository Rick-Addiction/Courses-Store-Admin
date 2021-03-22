package com.example.adapters.database.course;

import com.example.adapters.database.teacher.model.TeacherConverter;
import com.example.core.domain.course.Course;

import java.util.UUID;

public class CourseConverter {

    public static CourseModel toModel(Course course){
        CourseModel courseModel = new CourseModel();
        courseModel.setIdCourse(String.valueOf(course.getIdCourse()))   ;
        courseModel.setName(course.getName());
        courseModel.setOriginalValue(course.getOriginalValue());
        courseModel.setTeacherResponsible(TeacherConverter.toModel(course.getTeacherResponsible()));

        return courseModel;
    }

    public static Course toEntity(CourseModel courseModel){
        Course course = new Course();
        course.setIdCourse(UUID.fromString(courseModel.getIdCourse()));
        course.setName(courseModel.getName());
        course.setOriginalValue(courseModel.getOriginalValue());
        course.setTeacherResponsible(TeacherConverter.toEntity(courseModel.getTeacherResponsible()));

        return course;
    }

}
