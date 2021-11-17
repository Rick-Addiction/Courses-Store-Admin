package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.DesiredCourseRepository;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class FindAcquiredCoursesByCustomerTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database who acquired a course, When its searched for the courses they acquired, Then return the AcquiredCourses searched")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_who_acquired_a_course_When_its_searched_for_the_courses_they_acquired_Then_return_the_AcquiredCourse_searched() {

        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);
        AdapterUtils.registerANewAcquiredCourse(
                String.valueOf(customer.getIdCustomer()),
                course);

        ///Act
        FindAcquiredCoursesByCustomer findAcquiredCoursesByCustomer = new FindAcquiredCoursesByCustomer(acquiredCourseRepository);

        var acquiredCoursesList = findAcquiredCoursesByCustomer.findAcquiredCourses(
                String.valueOf(customer.getIdCustomer())
        );

        ///Assert
        assertNotNull(acquiredCoursesList);

    }

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database who doesn't acquired a course, When its searched for the courses they acquired, Then return an empty list")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_who_doesnt_acquired_a_course_When_its_searched_for_the_courses_they_acquired_Then_return_an_empty_list() {

        ///Arrange

        Customer customerWhoAcquiredACourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);
        AdapterUtils.registerANewAcquiredCourse(
                String.valueOf(customerWhoAcquiredACourse.getIdCustomer()),
                course);

        Customer customerWhoDoesntAcquiredACourse = AdapterUtils.registerANewCustomer();

        ///Act
        FindAcquiredCoursesByCustomer findAcquiredCoursesByCustomer = new FindAcquiredCoursesByCustomer(acquiredCourseRepository);

        var acquiredCoursesList = findAcquiredCoursesByCustomer.findAcquiredCourses(
                String.valueOf(customerWhoDoesntAcquiredACourse.getIdCustomer())
        );

        ///Assert
        assertEquals(0,acquiredCoursesList.size());

    }

}