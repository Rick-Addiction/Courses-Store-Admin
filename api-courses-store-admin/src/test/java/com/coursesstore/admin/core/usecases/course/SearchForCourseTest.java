package com.coursesstore.admin.core.usecases.course;

import com.coursesstore.admin.core.domain.course.FindCoursePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class SearchForCourseTest {

    @Mock
    private FindCoursePort findCoursePort;

    @Test
    @DisplayName("Given a Course id, When a new Course is searched, Then it should be done successfully")
    void Given_a_Course_id_When_a_new_Course_is_searched_Then_it_should_be_done_successfully(){

        ///Arrange
        SearchForCourse searchForCourse = new SearchForCourse(findCoursePort);

        ///Act
        searchForCourse.execute();

        ///Assert
        verify(findCoursePort, times(1)).findCourse();
    }
}