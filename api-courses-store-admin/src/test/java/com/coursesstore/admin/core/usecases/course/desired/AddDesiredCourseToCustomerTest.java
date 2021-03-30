package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
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
public class AddDesiredCourseToCustomerTest {
    @Mock
    private AddDesiredCoursePort addDesiredCoursePort;

    @Test
    @DisplayName("Given a Desired Course domain object, When its requested to be registered, Then it should be done successfully")
    public void Given_a_DesiredCourse_domain_object_When_its_requested_to_be_registered_Then_it_should_be_done_successfully(){

        ///Arrange
        AddDesiredCourseToCustomer addDesiredCourseToCustomer = new AddDesiredCourseToCustomer(addDesiredCoursePort);

        ///Act
        addDesiredCourseToCustomer.execute(new Customer());

        ///Assert
        verify(addDesiredCoursePort, times(1)).addNewDesiredCourseByCustomer(any(Customer.class));
    }
}