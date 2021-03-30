package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
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


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteAcquiredCourseTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a AcquiredCourse which is stored in the database, When its requested to delete the AcquiredCourse, Then it should be done successfully")
    public void Given_a_AcquiredCourse_stored_in_the_database_When_its_requested_to_delete_the_AcquiredCourse_Then_it_should_be_done_successfully () {

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
        DeleteAcquiredCourse deleteAcquiredCourse = new DeleteAcquiredCourse(acquiredCourseRepository);
        deleteAcquiredCourse.deleteAcquiredCourse(acquiredCourse);

        ///Assert
        Optional<AcquiredCourseModel> optionalDeletedAcquiredCourseModel = acquiredCourseRepository.findByIdAcquiredCourse(String.valueOf(acquiredCourse.getIdAcquiredCourse()));
        assertTrue(optionalDeletedAcquiredCourseModel.isEmpty());
    }


}