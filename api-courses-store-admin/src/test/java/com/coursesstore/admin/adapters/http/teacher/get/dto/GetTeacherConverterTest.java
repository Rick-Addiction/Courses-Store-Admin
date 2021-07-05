package com.coursesstore.admin.adapters.http.teacher.get.dto;

import com.coursesstore.admin.adapters.http.course.get.dto.GetCourseConverter;
import com.coursesstore.admin.adapters.http.course.get.dto.ResponseGetCourse;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class GetTeacherConverterTest {

    @Test
    @DisplayName("Given a valid List of Teacher entities domain, When these objects is converted to a ResponseGetTeacher, Then It should be done successfully")
    void Given_a_valid_RequestPutTeacher_When_the_object_is_converted_to_a_Course_entity_domain_Then_It_should_be_done_successfully() {

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

    @Test
    @DisplayName("Given a empty List of Teachers, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_empty_List_of_Teachers_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var teachers = new ArrayList<Teacher>();

        ///Act
        ResponseGetTeacher responseGetTeacher = GetTeacherConverter.toResponseGetTeacher(teachers);

        ///Assert
        assertNull(responseGetTeacher.getTeachers());
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = GetTeacherConverter.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> {
                    constructor.setAccessible(true);
                    try{
                        constructor.newInstance();
                    }
                    catch (InvocationTargetException e) {
                        throw (IllegalStateException) e.getTargetException();
                    }

                });

        assertEquals("Utility class",exception.getMessage());
    }


}