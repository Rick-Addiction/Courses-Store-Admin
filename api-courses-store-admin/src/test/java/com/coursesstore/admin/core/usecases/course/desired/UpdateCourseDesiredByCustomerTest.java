package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.UpdateDesiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class UpdateCourseDesiredByCustomerTest {
    @Mock
    private UpdateDesiredCoursePort updateDesiredCoursePort;

    @Test
    @DisplayName("When a new Desired Course is registered, So a new Desired Course record is created")
    public void When_a_new_DesiredCourse_is_registered_So_a_new_DesiredCourse_record_is_created(){

        ///Arrange
        UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer = new UpdateCourseDesiredByCustomer(updateDesiredCoursePort);

        ///Act
        updateCourseDesiredByCustomer.execute(new Customer());

        ///Assert
        verify(updateDesiredCoursePort, times(1)).updateDesiredCourse(any(Customer.class));
    }
}