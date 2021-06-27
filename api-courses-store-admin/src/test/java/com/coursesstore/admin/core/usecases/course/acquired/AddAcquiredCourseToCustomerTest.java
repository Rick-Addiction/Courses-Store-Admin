package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AddAcquiredCourseToCustomerTest {
    @Mock
    private AddAcquiredCoursePort addAcquiredCoursePort;

    @Mock
    private FindDesiredCoursePort findDesiredCourse;

    @Mock
    private DeleteDesiredCoursePort deleteDesiredCourse;

    @Test
    @DisplayName("Given a Acquired Course domain object, When its requested to be registered, Then it should be done successfully")
    void Given_a_AcquiredCourse_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){

        ///Arrange
        AddAcquiredCourseToCustomer addAcquiredCourseToCustomer = new AddAcquiredCourseToCustomer(
                findDesiredCourse,
                deleteDesiredCourse,
                addAcquiredCoursePort);

        ///Act
        addAcquiredCourseToCustomer.execute(
                String.valueOf(UUID.randomUUID()),
                DomainUtils.generateAcquiredCourse()
        );

        ///Assert
        verify(addAcquiredCoursePort, times(1)).addNewAcquiredCourseByCustomer(
                any(String.class),any(AcquiredCourse.class));
        verify(findDesiredCourse, times(1)).findDesiredCourse(
                any(String.class),any(String.class));
    }

    @Test
    @DisplayName("Given a Acquired Course domain object, " +
            "When it is requested the registration of a new acquisition, and the customer had already desired this course" +
            ", Then it should be the desire should be excluded and the acquisition registered")
    void Given_a_AcquiredCourse_domain_object_When_it_is_requested_the_registration_of_a_new_acquisition_and_the_customer_had_already_desired_this_course_Then_it_should_be_the_desire_should_be_excluded_and_the_acquisition_registered(){

        ///Arrange
        AddAcquiredCourseToCustomer addAcquiredCourseToCustomer = new AddAcquiredCourseToCustomer(
                findDesiredCourse,
                deleteDesiredCourse,
                addAcquiredCoursePort);

        when(findDesiredCourse.findDesiredCourse(anyString(),anyString())).thenReturn(new DesiredCourse());


        ///Act
        addAcquiredCourseToCustomer.execute(
                String.valueOf(UUID.randomUUID()),
                DomainUtils.generateAcquiredCourse()
        );

        ///Assert
        verify(addAcquiredCoursePort, times(1)).addNewAcquiredCourseByCustomer(
                any(String.class),any(AcquiredCourse.class));
        verify(findDesiredCourse, times(1)).findDesiredCourse(
                any(String.class),any(String.class));
        verify(deleteDesiredCourse, times(1)).deleteDesiredCourse(
                any(String.class),any(String.class));
    }
}