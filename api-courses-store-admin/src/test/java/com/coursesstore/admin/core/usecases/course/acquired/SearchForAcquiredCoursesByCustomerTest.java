package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursesByCustomerPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class SearchForAcquiredCoursesByCustomerTest {
    @Mock
    private FindAcquiredCoursesByCustomerPort findAcquiredCoursesByCustomerPort;

    @Test
    @DisplayName("Given a AcquiredCourse id, When a new AcquiredCourse is searched, Then it should be done successfully")
    public void Given_a_AcquiredCourse_id_When_a_new_AcquiredCourse_is_searched_Then_it_should_be_done_successfully(){

        ///Arrange
        SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer = new SearchForAcquiredCoursesByCustomer(findAcquiredCoursesByCustomerPort);

        ///Act
        searchForAcquiredCoursesByCustomer.execute("id_acquired_course");

        ///Assert
        verify(findAcquiredCoursesByCustomerPort, times(1)).findAcquiredCourses(any(String.class));
    }
}