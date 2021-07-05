package com.coursesstore.admin.adapters.http.course.post.dto;

import com.coursesstore.admin.adapters.http.customer.get.dto.GetCustomerConverter;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class PostCourseConverterTest {

    @Test
    @DisplayName("Given a valid RequestPostCourse dto, When this object is converted to a Course, Then It should be done successfully")
    void Given_a_valid_RequestPostCourse_dto_When_this_object_is_converted_to_a_Course_Then_It_should_be_done_successfully() {
        RequestPostCourse requestPostCourseExpected = DomainUtils.generateRequestPostCourse();

        Course courseActual = PostCourseConverter.toDomain(requestPostCourseExpected);

        assertEquals(requestPostCourseExpected.getName(),courseActual.getName());
        assertEquals(requestPostCourseExpected.getIdTeacherResponsible(),String.valueOf(courseActual.getTeacherResponsible().getIdTeacher()));
        assertEquals(requestPostCourseExpected.getOriginalValue(),String.valueOf(courseActual.getOriginalValue()));
    }


        @Test
    public void testPrivateConstructor() throws Exception {
        var constructor = PostCourseConverter.class.getDeclaredConstructor();
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