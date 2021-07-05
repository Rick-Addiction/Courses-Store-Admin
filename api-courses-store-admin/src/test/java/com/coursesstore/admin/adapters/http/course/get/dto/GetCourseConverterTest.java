package com.coursesstore.admin.adapters.http.course.get.dto;

import com.coursesstore.admin.adapters.http.customer.get.dto.GetCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.get.dto.ResponseGetDesiredCoursesByCustomer;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class GetCourseConverterTest {

    @Test
    @DisplayName("Given a valid List of Course entities domain, When these objects is converted to a ResponseGetCourse, Then It should be done successfully")
    void Given_a_valid_RequestPutCourse_When_the_object_is_converted_to_a_Course_entity_domain_Then_It_should_be_done_successfully() {

        ///Arrange
        List<Course> courses = List.of(
                DomainUtils.generateCourse(),
                DomainUtils.generateCourse()
        );

        ///Act
        ResponseGetCourse responseGetCourse = GetCourseConverter.toResponseGetCourse(courses);

        ///Assert
        assertEquals(responseGetCourse.getCourses().get(0).getIdCourse(), String.valueOf(courses.get(0).getIdCourse()));
        assertEquals(responseGetCourse.getCourses().get(0).getName(), String.valueOf(courses.get(0).getName()));
        assertEquals(responseGetCourse.getCourses().get(0).getOriginalValue(), String.valueOf(courses.get(0).getOriginalValue()));
        assertEquals(responseGetCourse.getCourses().get(0).getIdTeacherResponsible(), String.valueOf(courses.get(0).getTeacherResponsible().getIdTeacher()));
        assertEquals(responseGetCourse.getCourses().get(0).getTeacherResponsibleName(), courses.get(0).getTeacherResponsible().getName());

        assertEquals(responseGetCourse.getCourses().get(1).getIdCourse(), String.valueOf(courses.get(1).getIdCourse()));
        assertEquals(responseGetCourse.getCourses().get(1).getName(), String.valueOf(courses.get(1).getName()));
        assertEquals(responseGetCourse.getCourses().get(1).getOriginalValue(), String.valueOf(courses.get(1).getOriginalValue()));
        assertEquals(responseGetCourse.getCourses().get(1).getIdTeacherResponsible(), String.valueOf(courses.get(1).getTeacherResponsible().getIdTeacher()));
        assertEquals(responseGetCourse.getCourses().get(1).getTeacherResponsibleName(), courses.get(1).getTeacherResponsible().getName());

    }

    @Test
    @DisplayName("Given a empty List of Courses, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_empty_List_of_Courses_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var courses = new ArrayList<Course>();

        ///Act
        ResponseGetCourse responseGetCourse = GetCourseConverter.toResponseGetCourse(courses);

        ///Assert
        assertNull(responseGetCourse.getCourses());
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = GetCourseConverter.class.getDeclaredConstructor();
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