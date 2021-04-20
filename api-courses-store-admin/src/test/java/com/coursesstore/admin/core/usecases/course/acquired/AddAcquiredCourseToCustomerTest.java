package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class AddAcquiredCourseToCustomerTest {
    @Mock
    private AddAcquiredCoursePort addAcquiredCoursePort;

    @Mock
    private FindCoursePort findCoursePort;

    @Test
    @DisplayName("Given a Acquired Course domain object, When its requested to be registered, Then it should be done successfully")
    public void Given_a_AcquiredCourse_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){

        ///Arrange
        AddAcquiredCourseToCustomer addAcquiredCourseToCustomer = new AddAcquiredCourseToCustomer(addAcquiredCoursePort,findCoursePort);

        when(findCoursePort.findCourse(anyString())).thenReturn(DomainUtils.generateCourse());

        ///Act
        addAcquiredCourseToCustomer.execute(DomainUtils.generateCustomerWithAnAcquiredCourse());

        ///Assert
        verify(addAcquiredCoursePort, times(1)).addNewAcquiredCourseByCustomer(any(Customer.class));
    }
}