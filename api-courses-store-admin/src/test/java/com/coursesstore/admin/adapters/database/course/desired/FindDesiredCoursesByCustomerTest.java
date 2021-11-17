package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.FindDesiredCoursesByCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class FindDesiredCoursesByCustomerTest {

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database who desired a course, When its searched for the courses they desired, Then return the desiredCourses searched")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_who_desired_a_course_When_its_searched_for_the_courses_they_desired_Then_return_the_desiredCourse_searched() {

        ///Arrange
        Customer customerWithADesiredCourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        String idCostumer = String.valueOf(customerWithADesiredCourse.getIdCustomer());

        DesiredCourse desiredCourse = AdapterUtils.registerANewDesiredCourse(
                idCostumer,
                course);

        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,customerRepository,courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(
                idCostumer,
                desiredCourse);

        ///Act
        FindDesiredCoursesByCustomer findDesiredCoursesByCustomer = new FindDesiredCoursesByCustomer(desiredCourseRepository);

        var desiredCoursesByCustomerList = findDesiredCoursesByCustomer.findDesiredCourses(
                String.valueOf(course.getIdCourse())
        );

        ///Assert
        assertNotNull(desiredCoursesByCustomerList);
    }

    @Test
    @DisplayName("Given a valid id of an Customer stored in the database who doesn't desired a course, When its searched for the courses they desired, Then return an empty list")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_who_doesnt_desired_a_course_When_its_searched_for_the_courses_they_desired_Then_return_an_empty_list() {

        ///Arrange

        Customer customerWhoDesiredACourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);
        AdapterUtils.registerANewDesiredCourse(
                String.valueOf(customerWhoDesiredACourse.getIdCustomer()),
                course);

        Customer customerWhoDoesntDesiredACourse = AdapterUtils.registerANewCustomer();

        ///Act
        FindDesiredCoursesByCustomer findDesiredCoursesByCustomer = new FindDesiredCoursesByCustomer(desiredCourseRepository);

        var desiredCoursesList = findDesiredCoursesByCustomer.findDesiredCourses(
                String.valueOf(customerWhoDoesntDesiredACourse.getIdCustomer())
        );

        ///Assert
        assertEquals(0,desiredCoursesList.size());

    }

}