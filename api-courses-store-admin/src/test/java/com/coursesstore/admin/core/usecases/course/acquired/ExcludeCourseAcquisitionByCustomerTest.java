package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ExcludeCourseAcquisitionByCustomerTest {
    @Mock
    private DeleteAcquiredCoursePort deleteAcquiredCoursePort;

    @Test
    @DisplayName("Given a Acquired Course domain object, When this Acquired Course is excluded, Then it should be done successfully")
    void Given_a_AcquiredCourse_domain_object_When_this_AcquiredCourse_is_excluded_Then_it_should_be_done_successfully(){

        ///Arrange
        ExcludeCourseAcquisitionByCustomer excludeCourseAcquisitionByCustomer = new ExcludeCourseAcquisitionByCustomer(deleteAcquiredCoursePort);

        ///Act
        excludeCourseAcquisitionByCustomer.execute("id_customer","id_course");

        ///Assert
        verify(deleteAcquiredCoursePort, times(1)).deleteAcquiredCourse(any(String.class),any(String.class));
    }
}