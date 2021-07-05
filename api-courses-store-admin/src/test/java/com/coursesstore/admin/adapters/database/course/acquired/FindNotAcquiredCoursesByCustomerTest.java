package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8106"})
class FindNotAcquiredCoursesByCustomerTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Test
    @DisplayName("Given a valid id of an Customer stored in the database, When its searched for the courses not acquired by the Customer, Then return the List of Courses not acquired by customer")
    void Given_a_valid_id_of_an_Customer_stored_in_the_database_When_its_searched_for_the_courses_not_acquired_by_the_Customer_Then_return_the_List_of_Courses_not_acquired_by_customer() {

        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course1 = AdapterUtils.registerANewCourse(teacher);
        Course course2 = AdapterUtils.registerANewCourse(teacher);

        ///Act
        FindNotAcquiredCoursesByCustomer findNotAcquiredCoursesByCustomer =
                new FindNotAcquiredCoursesByCustomer(acquiredCourseRepository, courseRepository);

        var acquiredCoursesList = findNotAcquiredCoursesByCustomer.findNotAcquiredCourses(
                String.valueOf(customer.getIdCustomer())
        );

        ///Assert
        assertNotNull(acquiredCoursesList);
        assertEquals(2, acquiredCoursesList.size());

        assertEquals(course1.getIdCourse(), acquiredCoursesList.get(0).getIdCourse());
        assertEquals(course1.getName(), acquiredCoursesList.get(0).getName());
        assertEquals(course1.getOriginalValue(), acquiredCoursesList.get(0).getOriginalValue());
        assertEquals(course1.getTeacherResponsible().getIdTeacher(), acquiredCoursesList.get(0).getTeacherResponsible().getIdTeacher());
        assertEquals(course1.getTeacherResponsible().getName(), acquiredCoursesList.get(0).getTeacherResponsible().getName());

        assertEquals(course2.getIdCourse(), acquiredCoursesList.get(1).getIdCourse());
        assertEquals(course2.getName(), acquiredCoursesList.get(1).getName());
        assertEquals(course2.getOriginalValue(), acquiredCoursesList.get(1).getOriginalValue());
        assertEquals(course2.getTeacherResponsible().getIdTeacher(), acquiredCoursesList.get(1).getTeacherResponsible().getIdTeacher());
        assertEquals(course2.getTeacherResponsible().getName(), acquiredCoursesList.get(1).getTeacherResponsible().getName());

    }
}