package com.coursesstore.admin.adapters.database.teacher.model;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class TeacherConverterTest {

    @Test
    @DisplayName("Given a valid Teacher entity, When the object is converted to a Teacher model, Then It should be done successfully")
    void Given_a_valid_Teacher_entity_When_the_object_is_converted_to_a_Teacher_model_Then_It_should_be_done_successfully(){

        ///Arrange
        Teacher teacher = DomainUtils.generateTeacher();

        ///Act
        TeacherModel teacherModel = TeacherConverter.toModel(teacher);

        ///Assert
        assertEquals(teacherModel.getIdTeacher(), String.valueOf(teacher.getIdTeacher()));
        assertEquals(teacherModel.getName(),teacher.getName());

    }

    @Test
    @DisplayName("Given a valid Teacher model, When the object is converted to a Teacher entity, Then It should be done successfully")
    void Given_a_valid_Teacher_model_When_the_object_is_converted_to_a_Teacher_entity_Then_It_should_be_done_successfully(){

        ///Arrange
        TeacherModel teacherModel = DomainUtils.generateTeacherModel();

        ///Act
        Teacher teacher = TeacherConverter.toEntity(teacherModel);

        ///Assert
        assertEquals(String.valueOf(teacher.getIdTeacher()), teacherModel.getIdTeacher());
        assertEquals(teacher.getName(),teacherModel.getName());

    }    

}