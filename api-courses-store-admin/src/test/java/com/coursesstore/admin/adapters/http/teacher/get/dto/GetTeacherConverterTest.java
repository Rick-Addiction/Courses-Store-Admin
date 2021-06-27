package com.coursesstore.admin.adapters.http.teacher.get.dto;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTeacherConverterTest {

    @Test
    @DisplayName("Given a valid List of Teacher entities domain, When these objects is converted to a ResponseGetTeacher, Then It should be done successfully")
    public void Given_a_valid_RequestPutTeacher_When_the_object_is_converted_to_a_Course_entity_domain_Then_It_should_be_done_successfully() {

        ///Arrange
        List<Teacher> teachers = List.of(
                DomainUtils.generateTeacher(),
                DomainUtils.generateTeacher()
        );

        ///Act
        ResponseGetTeacher responseGetTeacher = GetTeacherConverter.toResponseGetTeacher(teachers);

        ///Assert
        assertEquals(responseGetTeacher.getTeachers().get(0).getIdTeacher(), String.valueOf(teachers.get(0).getIdTeacher()));
        assertEquals(responseGetTeacher.getTeachers().get(0).getName(), String.valueOf(teachers.get(0).getName()));

        assertEquals(responseGetTeacher.getTeachers().get(1).getIdTeacher(), String.valueOf(teachers.get(1).getIdTeacher()));
        assertEquals(responseGetTeacher.getTeachers().get(1).getName(), String.valueOf(teachers.get(1).getName()));

    }


}