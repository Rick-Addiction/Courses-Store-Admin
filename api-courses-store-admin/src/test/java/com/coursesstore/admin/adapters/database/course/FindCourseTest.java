package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.acquired.AddAcquiredCourse;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class FindCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an Course stored in the database, When its searched for this Course, Then return the Course searched")
    void Given_a_valid_id_of_an_Course_stored_in_the_database_When_its_searched_for_this_Course_Then_return_the_Course_searched() {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        ///Act
        FindCourse findCourse = new FindCourse(courseRepository);

        var coursesList = findCourse.findCourse(
                String.valueOf(course.getIdCourse())
        );

        ///Assert
        assertNotNull(coursesList);
    }

    @Test
    @DisplayName("Given a valid id of an Course stored in the database, When its searched for this Course, Then return the Course searched")
    void Given_no_id_of_an_Course_stored_in_the_database_When_its_searched_for_all_the_Courses_Then_return_the_Course_searched() {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        ///Act
        FindCourse findCourse = new FindCourse(courseRepository);

        var coursesList = findCourse.findCourse();

        ///Assert
        assertNotNull(coursesList);
    }

    @Test
    @DisplayName("Given an invalid Course domain, When it is tried to find this Course, Then it will throw a DataNotFoundException")
    void Given_an_invalid_Course_domain_When_it_is_tried_to_find_this_Course_Then_it_will_throw_a_DataNotFoundException() {
        ///Arrange
        Course course = new Course();
        course.setIdCourse(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        ///Act
        FindCourse findCourse = new FindCourse(courseRepository);


        ///Assert
        DataNotFoundException exception = assertThrows(
                DataNotFoundException.class,
                () -> findCourse.findCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"
                )
        );

        assertEquals("Course not found -  Course d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }

}