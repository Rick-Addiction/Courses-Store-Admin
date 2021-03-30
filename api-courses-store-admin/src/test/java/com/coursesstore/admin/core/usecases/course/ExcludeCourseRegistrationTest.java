package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.DeleteCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ExcludeCourseRegistrationTest {

    @Mock
    private DeleteCoursePort deleteCoursePort;

    @Test
    @DisplayName("Given a Course domain object, When this Course is excluded, Then it should be done successfully")
    public void Given_a_Course_domain_object_When_this_Course_is_excluded_Then_it_should_be_done_successfully(){

        ///Arrange
        ExcludeCourseRegistration excludeCourseRegistration = new ExcludeCourseRegistration(deleteCoursePort);

        ///Act
        excludeCourseRegistration.execute(new Course());

        ///Assert
        verify(deleteCoursePort, times(1)).deleteCourse(any(Course.class));
    }
}