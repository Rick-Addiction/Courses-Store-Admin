package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class ExcludeCourseDesiredByCustomerTest {
    @Mock
    private DeleteDesiredCoursePort deleteDesiredCoursePort;

    @Test
    @DisplayName("Given a Desired Course domain object, When this Desired Course is excluded, Then it should be done successfully")
    public void Given_a_DesiredCourse_domain_object_When_this_DesiredCourse_is_excluded_Then_it_should_be_done_successfully(){

        ///Arrange
        ExcludeCourseDesiredByCustomer excludeCourseDesiredByCustomer = new ExcludeCourseDesiredByCustomer(deleteDesiredCoursePort);

        ///Act
        excludeCourseDesiredByCustomer.execute(new DesiredCourse());

        ///Assert
        verify(deleteDesiredCoursePort, times(1)).deleteDesiredCourse(any(DesiredCourse.class));
    }
}