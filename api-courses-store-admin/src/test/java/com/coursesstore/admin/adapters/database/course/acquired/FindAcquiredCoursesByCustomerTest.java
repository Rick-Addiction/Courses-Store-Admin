package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class FindAcquiredCoursesByCustomerTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an AcquiredCourse stored in the database, When its searched for this Course, Then return the AcquiredCourse searched")
    public void Given_a_valid_id_of_an_AcquiredCourse_stored_in_the_database_When_its_searched_for_this_Course_Then_return_the_AcquiredCourse_searched() {

        ///Arrange
        Customer customerThatAcquiredACourse = DomainUtils.generateCustomerWithAnAcquiredCourse();
        AcquiredCourse acquiredCourse = customerThatAcquiredACourse.getAcquiredCourses().iterator().next();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(acquiredCourse.getCourse().getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(acquiredCourse.getCourse());

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customerThatAcquiredACourse);

        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository,customerRepository);
        addAcquiredCourse.addNewAcquiredCourseByCustomer(customerThatAcquiredACourse);

        ///Act
        FindAcquiredCoursesByCustomer findAcquiredCoursesByCustomer = new FindAcquiredCoursesByCustomer(acquiredCourseRepository);

        ///Assert
        //TODO Set verify
    }

}