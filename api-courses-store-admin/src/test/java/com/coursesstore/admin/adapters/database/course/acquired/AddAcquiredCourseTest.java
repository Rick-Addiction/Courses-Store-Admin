package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class AddAcquiredCourseTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName(
            "Given a valid AcquiredCourse domain object, " +
            "When the acquiredCourse is not in the database and there is not a desire of this course for the same Customer, " +
            "Then just create a new acquiredCourse")
    void Given_a_valid_AcquiredCourse_domain_object_When_the_acquiredCourse_is_not_in_the_database_Then_create_a_new_acquiredCourse() {

        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        AcquiredCourse acquiredCourse = DomainUtils.generateAcquiredCourse(course);
        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        ///Act
        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository,customerRepository, courseRepository);
        addAcquiredCourse.addNewAcquiredCourseByCustomer(
                String.valueOf(customer.getIdCustomer()),
                acquiredCourse
        );

        ///Assert
        AcquiredCourseKey acquiredCourseKey = new AcquiredCourseKey(String.valueOf(customer.getIdCustomer()),
                String.valueOf(acquiredCourse.getCourse().getIdCourse()));
        Optional<AcquiredCourseModel> optionalAcquiredCourseModel = acquiredCourseRepository.findById(acquiredCourseKey);

        assertTrue(optionalAcquiredCourseModel.isPresent());

        AcquiredCourseModel acquiredCourseModel = optionalAcquiredCourseModel.get();
        Customer customerThatAcquiredTheCourse = AcquiredCourseConverter.toCustomerWithEntity(acquiredCourseModel);
        AcquiredCourse acquiredCourseAdded = customerThatAcquiredTheCourse.getAcquiredCourses().iterator().next();


        assertEquals(acquiredCourse.getAcquisitionDate(), acquiredCourseAdded.getAcquisitionDate());
        assertEquals(acquiredCourse.getValuePaid(), acquiredCourseAdded.getValuePaid());


        Course courseCreated = acquiredCourseAdded.getCourse();
        assertEquals(course.getIdCourse(), courseCreated.getIdCourse());
        assertEquals(course.getName(), courseCreated.getName());
        assertEquals(course.getOriginalValue(), courseCreated.getOriginalValue());
        assertEquals(course.getTeacherResponsible().getIdTeacher(), courseCreated.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(), courseCreated.getTeacherResponsible().getName());


        assertEquals(customer.getIdCustomer(), customerThatAcquiredTheCourse.getIdCustomer());
        assertEquals(customer.getFirstname(), customerThatAcquiredTheCourse.getFirstname());
        assertEquals(customer.getLastname(), customerThatAcquiredTheCourse.getLastname());
        assertEquals(customer.getPhone(), customerThatAcquiredTheCourse.getPhone());
        assertEquals(customer.getEmail(), customerThatAcquiredTheCourse.getEmail());
        assertEquals(customer.getLinkedIn(), customerThatAcquiredTheCourse.getLinkedIn());
        assertEquals(customer.getCompany(), customerThatAcquiredTheCourse.getCompany());
        assertEquals(customer.getPosition(), customerThatAcquiredTheCourse.getPosition());
    }

    @Test
    @DisplayName(
            "Given a AcquiredCourse with an invalid Course domain, When it is tried to add this new acquiredCourse, Then it will throw a ModelException")
    void Given_a_AcquiredCourse_with_an_invalid_Course_domain_When_it_is_tried_to_add_this_new_acquiredCourse_Then_it_will_throw_a_ModelException() {
        ///Arrange
        Customer customer = AdapterUtils.registerANewCustomer();
        Course course = new Course();
        course.setIdCourse(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        AcquiredCourse acquiredCourse = DomainUtils.generateAcquiredCourse(course);

        ///Act
        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository,customerRepository, courseRepository);


        ModelException exception = assertThrows(
                ModelException.class,
                () -> addAcquiredCourse.addNewAcquiredCourseByCustomer(
                        String.valueOf(customer.getIdCustomer()),
                        acquiredCourse
                )
        );

        assertEquals("Conflict at the adding of a new Acquired Course: Course not found -  Course d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }

    @Test
    @DisplayName(
            "Given an invalid Customer domain, When it is tried to add this new acquiredCourse, Then it will throw a ModelException")
    void Given_an_invalid_Customer_domain_When_it_is_tried_to_add_this_new_acquiredCourse_Then_it_will_throw_a_ModelException() {
        ///Arrange
        Customer customer = DomainUtils.generateCustomer();
        customer.setIdCustomer(UUID.fromString("5db4a656-5694-4c9b-b1f2-2fac451bd29f"));
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        AcquiredCourse acquiredCourse = DomainUtils.generateAcquiredCourse(course);

        ///Act
        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository,customerRepository, courseRepository);


        ModelException exception = assertThrows(
                ModelException.class,
                () -> addAcquiredCourse.addNewAcquiredCourseByCustomer(
                        String.valueOf(customer.getIdCustomer()),
                        acquiredCourse
                )
        );

        assertEquals("Conflict at the adding of a new Acquired Course: Customer not found -  Customer 5db4a656-5694-4c9b-b1f2-2fac451bd29f!",exception.getMessage());
    }
}