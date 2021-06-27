package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddDesiredCourseToCustomerTest {
    @Mock
    private AddDesiredCoursePort addDesiredCoursePort;

    @Mock
    private FindAcquiredCoursePort findAcquiredCourse;

    @Mock
    private DeleteAcquiredCoursePort deleteAcquiredCourse;

    @Test
    @DisplayName("Given a Desired Course domain object, When its requested to be registered, Then it should be done successfully")
    public void Given_a_DesiredCourse_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){

        ///Arrange
        AddDesiredCourseToCustomer addDesiredCourseToCustomer = new AddDesiredCourseToCustomer(
                addDesiredCoursePort,
                findAcquiredCourse,
                deleteAcquiredCourse
        );

        ///Act
        addDesiredCourseToCustomer.execute(
                String.valueOf(UUID.randomUUID()),
                DomainUtils.generateDesiredCourse());

        ///Assert
        verify(addDesiredCoursePort, times(1)).addNewDesiredCourseByCustomer(
                any(String.class),
                any(DesiredCourse.class)
        );
        verify(findAcquiredCourse, times(1)).findAcquiredCourse(
                any(String.class),any(String.class));
    }

    @Test
    @DisplayName("Given a Desired Course domain object, " +
            "When it is requested the registration of a new desired, and the customer had already acquired this course" +
            ", Then the acquisition should be excluded and the desire registered")
    public void Given_a_AcquiredCourse_domain_object_When_it_is_requested_the_registration_of_a_new_acquisition_and_the_customer_had_already_desired_this_course_Then_it_should_be_the_desire_should_be_excluded_and_the_acquisition_registered() {

        ///Arrange
        AddDesiredCourseToCustomer addDesiredCourseToCustomer = new AddDesiredCourseToCustomer(
                addDesiredCoursePort,
                findAcquiredCourse,
                deleteAcquiredCourse
        );

        when(findAcquiredCourse.findAcquiredCourse(anyString(),anyString())).thenReturn(new AcquiredCourse());

        ///Act
        addDesiredCourseToCustomer.execute(
                String.valueOf(UUID.randomUUID()),
                DomainUtils.generateDesiredCourse());

        ///Assert
        verify(addDesiredCoursePort, times(1)).addNewDesiredCourseByCustomer(
                any(String.class),
                any(DesiredCourse.class)
        );
        verify(findAcquiredCourse, times(1)).findAcquiredCourse(
                any(String.class),any(String.class));
        verify(deleteAcquiredCourse, times(1)).deleteAcquiredCourse(
                any(String.class),any(String.class));
    }
}