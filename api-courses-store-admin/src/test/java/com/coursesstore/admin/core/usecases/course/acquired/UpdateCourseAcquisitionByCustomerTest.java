package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.UpdateAcquiredCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UpdateCourseAcquisitionByCustomerTest {
    @Mock
    private UpdateAcquiredCoursePort updateAcquiredCoursePort;

    @Test
    @DisplayName("When a new Acquired Course is registered, So a new Acquired Course record is created")
    public void When_a_new_AcquiredCourse_is_registered_So_a_new_AcquiredCourse_record_is_created(){

        ///Arrange
        UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer = new UpdateCourseAcquisitionByCustomer(updateAcquiredCoursePort);

        ///Act
        updateCourseAcquisitionByCustomer.execute(
                String.valueOf(UUID.randomUUID()),
                DomainUtils.generateAcquiredCourse());

        ///Assert
        verify(updateAcquiredCoursePort, times(1)).updateAcquiredCourse(
                any(String.class),any(AcquiredCourse.class));
    }
}