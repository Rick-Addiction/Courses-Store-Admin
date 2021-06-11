package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class FindDesiredCoursesByCustomerTest {

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an DesiredCourse stored in the database, When its searched for this Course, Then return the DesiredCourse searched")
    public void Given_a_valid_id_of_an_DesiredCourse_stored_in_the_database_When_its_searched_for_this_Course_Then_return_the_DesiredCourse_searched() {

        ///Arrange
        Customer customerWithADesiredCourse = DomainUtils.generateCustomerWithADesiredCourse();
        DesiredCourse desiredCourse = customerWithADesiredCourse.getDesiredCourses().iterator().next();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(desiredCourse.getCourse().getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(desiredCourse.getCourse());

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customerWithADesiredCourse);

        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(customerWithADesiredCourse);

        ///Act
        FindDesiredCoursesByCustomer findDesiredCoursesByCustomer = new FindDesiredCoursesByCustomer(desiredCourseRepository);

        ///Assert
        //TODO Set verify
    }

}