package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.DeleteCourse;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class DeleteAcquiredCourseTest {

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
    @DisplayName("Given a AcquiredCourse which is stored in the database, When its requested to delete the AcquiredCourse, Then it should be done successfully")
    void Given_a_AcquiredCourse_stored_in_the_database_When_its_requested_to_delete_the_AcquiredCourse_Then_it_should_be_done_successfully () {

        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);
        AdapterUtils.registerANewAcquiredCourse(
                String.valueOf(customer.getIdCustomer()),
                course);

        ///Act
        DeleteAcquiredCourse deleteAcquiredCourse = new DeleteAcquiredCourse(acquiredCourseRepository);
        deleteAcquiredCourse.deleteAcquiredCourse(
                String.valueOf(customer.getIdCustomer()),
                String.valueOf(course.getIdCourse()));

        ///Assert
        AcquiredCourseKey acquiredCourseKey = new AcquiredCourseKey(String.valueOf(customer.getIdCustomer()),
                String.valueOf(course.getIdCourse()));
        Optional<AcquiredCourseModel> optionalDeletedAcquiredCourseModel = acquiredCourseRepository.findById(acquiredCourseKey);
        assertTrue(optionalDeletedAcquiredCourseModel.isEmpty());
    }

    @Test
    @DisplayName("Given an invalid AcquiredCourse domain, When it is tried to delete this AcquiredCourse, Then it will throw a ModelException")
    void Given_an_invalid_AcquiredCourse_domain_When_it_is_tried_to_delete_this_AcquiredCourse_Then_it_will_throw_a_ModelException() {
        ///Act
        DeleteAcquiredCourse deleteAcquiredCourse = new DeleteAcquiredCourse(acquiredCourseRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> deleteAcquiredCourse.deleteAcquiredCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b",
                        "38885619-b397-4dd1-b029-61fc35f55427"
                )
        );

        assertEquals("Acquired Course not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b, Course 38885619-b397-4dd1-b029-61fc35f55427!",exception.getMessage());
    }


}