package com.coursesstore.admin.adapters.http.teacher.post.dto;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTeacherConverterTest {

    @Test
    @DisplayName("Given a valid RequestPostTeacher, When the object is converted to a Course entity domain, Then It should be done successfully")
    public void Given_a_valid_RequestPostTeacher_When_the_object_is_converted_to_a_Course_entity_domain_Then_It_should_be_done_successfully() {

        ///Arrange
        RequestPostTeacher requestPostTeacher = DomainUtils.generateRequestPostTeacher();

        ///Act
        Teacher teacher = PostTeacherConverter.toDomain(requestPostTeacher);

        ///Assert
        assertEquals(teacher.getName(), requestPostTeacher.getName());
    }
}