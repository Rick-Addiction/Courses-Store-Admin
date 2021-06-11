package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.UpdateCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UpdateCourseRegistrationTest {

    @Mock
    private UpdateCoursePort updateCoursePort;

    @Test
    @DisplayName("Given a Course domain object, When this Course is updated, Then it should be done successfully")
    public void Given_a_Course_domain_object_When_this_Course_is_updated_Then_it_should_be_done_successfully(){

        ///Arrange
        UpdateCourseRegistration updateCourseRegistration = new UpdateCourseRegistration(updateCoursePort);

        ///Act
        updateCourseRegistration.execute(new Course());

        ///Assert
        verify(updateCoursePort, times(1)).updateCourse(any(Course.class));
    }
}