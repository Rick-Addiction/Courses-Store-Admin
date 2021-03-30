package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteDesiredCourseTest {

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a DesiredCourse which is stored in the database, When its requested to delete the DesiredCourse, Then it should be done successfully")
    public void Given_a_DesiredCourse_stored_in_the_database_When_its_requested_to_delete_the_DesiredCourse_Then_it_should_be_done_successfully () {

        ///Arrange
        Customer customerWithADesiredCourse = DomainUtils.generateCustomerWithADesiredCourse();
        DesiredCourse desiredCourse = customerWithADesiredCourse.getDesiredCourses().iterator().next();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(desiredCourse.getCourse().getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(desiredCourse.getCourse());

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customerWithADesiredCourse);

        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(customerWithADesiredCourse);

        ///Act
        DeleteDesiredCourse deleteDesiredCourse = new DeleteDesiredCourse(desiredCourseRepository);
        deleteDesiredCourse.deleteDesiredCourse(desiredCourse);

        ///Assert
        Optional<DesiredCourseModel> optionalDeletedDesiredCourseModel = desiredCourseRepository.findByIdDesiredCourse(String.valueOf(desiredCourse.getIdDesiredCourse()));
        assertTrue(optionalDeletedDesiredCourseModel.isEmpty());
    }


}