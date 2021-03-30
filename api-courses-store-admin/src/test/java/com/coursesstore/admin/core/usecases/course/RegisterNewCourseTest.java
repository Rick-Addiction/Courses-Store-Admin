package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.CreateCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class RegisterNewCourseTest {

    @Mock
    private CreateCoursePort createCoursePort;

    @Test
    @DisplayName("Given a Course domain object, When its requested to be registered, Then it should be done successfully")
    public void Given_a_Course_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){

        ///Arrange
        RegisterNewCourse registerNewCourse = new RegisterNewCourse(createCoursePort);

        ///Act
        registerNewCourse.execute(new Course());

        ///Assert
        verify(createCoursePort, times(1)).createCourse(any(Course.class));
    }
}