package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursesByCustomerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class SearchForDesiredCoursesByCustomerTest {

    @Mock
    private FindDesiredCoursesByCustomerPort findDesiredCoursesByCustomerPort;

    @Test
    @DisplayName("Given a DesiredCourse id, When a new DesiredCourse is searched, Then it should be done successfully")
    public void Given_a_DesiredCourse_did_When_a_new_DesiredCourse_is_searched_Then_it_should_be_done_successfully(){

        ///Arrange
        SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer = new SearchForDesiredCoursesByCustomer(findDesiredCoursesByCustomerPort);

        ///Act
        searchForDesiredCoursesByCustomer.execute("id_desired_course");

        ///Assert
        verify(findDesiredCoursesByCustomerPort, times(1)).findDesiredCourse(any(String.class));
    }
}