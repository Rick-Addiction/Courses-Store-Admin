package com.coursesstore.admin.adapters.database.course.model;


import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class CourseConverterTest {

    @Test
    @DisplayName("Given a valid Course entity, When the object is converted to a Course model, Then It should be done successfully")
    public void Given_a_valid_Course_entity_When_the_object_is_converted_to_a_Course_model_Then_It_should_be_done_successfully(){

        ///Arrange
        Course course = DomainUtils.generateCourse();

        ///Act
        CourseModel courseModel = CourseConverter.toModel(course);

        ///Assert
        assertEquals(courseModel.getIdCourse(), String.valueOf(course.getIdCourse()));
        assertEquals(courseModel.getName(),course.getName());
        assertEquals(courseModel.getOriginalValue(),course.getOriginalValue());
        assertEquals(courseModel.getTeacherResponsible().getIdTeacher(),String.valueOf(course.getTeacherResponsible().getIdTeacher()));
        assertEquals(courseModel.getTeacherResponsible().getName(),course.getTeacherResponsible().getName());
    }

    @Test
    @DisplayName("Given a valid Course model, When the object is converted to a Course entity, Then It should be done successfully")
    public void Given_a_valid_Course_model_When_the_object_is_converted_to_a_Course_entity_Then_It_should_be_done_successfully(){

        ///Arrange
        CourseModel courseModel = DomainUtils.generateCourseModel();

        ///Act
        Course course = CourseConverter.toEntity(courseModel);


        ///Assert
        assertEquals(String.valueOf(course.getIdCourse()), courseModel.getIdCourse());
        assertEquals(course.getName(),courseModel.getName());
        assertEquals(course.getOriginalValue(),courseModel.getOriginalValue());
        assertEquals(String.valueOf(course.getTeacherResponsible().getIdTeacher()),courseModel.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(),courseModel.getTeacherResponsible().getName());

    }

}