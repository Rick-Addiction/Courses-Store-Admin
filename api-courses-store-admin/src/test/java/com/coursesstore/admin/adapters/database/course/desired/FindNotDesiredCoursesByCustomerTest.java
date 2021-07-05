package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.DesiredCourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.FindNotDesiredCoursesByCustomer;
import com.coursesstore.admin.core.domain.course.Course;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.h2.console.enabled=true"})
class FindNotDesiredCoursesByCustomerTest {

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    @DisplayName("Given a valid id of an Customer stored in the database, When its searched for the courses not desired by the Customer, Then return the List of Courses not desired by customer")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_When_its_searched_for_the_courses_not_desired_by_the_Customer_Then_return_the_List_of_Courses_not_desired_by_customer() {

        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course1 = AdapterUtils.registerANewCourse(teacher);
        Course course2 = AdapterUtils.registerANewCourse(teacher);

        ///Act
        FindNotDesiredCoursesByCustomer findNotDesiredCoursesByCustomer =
                new FindNotDesiredCoursesByCustomer(desiredCourseRepository, courseRepository);

        var desiredCoursesList = findNotDesiredCoursesByCustomer.findNotDesiredCourses(
                String.valueOf(customer.getIdCustomer())
        );

        ///Assert
        assertNotNull(desiredCoursesList);
        assertEquals(2, desiredCoursesList.size());

        assertEquals(course1.getIdCourse(), desiredCoursesList.get(0).getIdCourse());
        assertEquals(course1.getName(), desiredCoursesList.get(0).getName());
        assertEquals(course1.getOriginalValue(), desiredCoursesList.get(0).getOriginalValue());
        assertEquals(course1.getTeacherResponsible().getIdTeacher(), desiredCoursesList.get(0).getTeacherResponsible().getIdTeacher());
        assertEquals(course1.getTeacherResponsible().getName(), desiredCoursesList.get(0).getTeacherResponsible().getName());

        assertEquals(course2.getIdCourse(), desiredCoursesList.get(1).getIdCourse());
        assertEquals(course2.getName(), desiredCoursesList.get(1).getName());
        assertEquals(course2.getOriginalValue(), desiredCoursesList.get(1).getOriginalValue());
        assertEquals(course2.getTeacherResponsible().getIdTeacher(), desiredCoursesList.get(1).getTeacherResponsible().getIdTeacher());
        assertEquals(course2.getTeacherResponsible().getName(), desiredCoursesList.get(1).getTeacherResponsible().getName());

    }
}