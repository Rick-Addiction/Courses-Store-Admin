package com.coursesstore.admin.adapters.http.teacher.put.dto;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutTeacherConverterTest {

    @Test
    @DisplayName("Given a valid RequestPutTeacher, When the object is converted to a Teacher entity domain, Then It should be done successfully")
    public void Given_a_valid_RequestPutTeacher_When_the_object_is_converted_to_a_Teacher_entity_domain_Then_It_should_be_done_successfully() {

        ///Arrange
        String idTeacher = String.valueOf(UUID.randomUUID());
        RequestPutTeacher requestPutTeacher = DomainUtils.generateRequestPutTeacher(idTeacher);

        ///Act
        Teacher teacher = PutTeacherConverter.toDomain(requestPutTeacher);

        ///Assert
        assertEquals(teacher.getIdTeacher(), UUID.fromString(requestPutTeacher.getIdTeacher()));
        assertEquals(teacher.getName(), requestPutTeacher.getName());
    }
}