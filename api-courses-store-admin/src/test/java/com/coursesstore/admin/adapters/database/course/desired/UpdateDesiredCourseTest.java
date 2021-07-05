package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.UpdateCourse;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class UpdateDesiredCourseTest {

    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid DesiredCourse stored in the database, When its requested to update the DesiredCourse, Then it should be done successfully")
    void Given_a_valid_DesiredCourse_stored_in_the_database_When_its_requested_to_update_the_DesiredCourse_Then_it_should_be_done_successfully() {

        ///Arrange
        Customer customerWithADesiredCourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        DesiredCourse desiredCourse = DomainUtils.generateDesiredCourse(course);

        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,customerRepository,courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(
                String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                desiredCourse);

        ///Act
        DesiredCourse desiredCourseToUpdate = desiredCourse;
        desiredCourseToUpdate.setDesireDescription("He wants to increase his knowledge");

        UpdateDesiredCourse updateDesiredCourse = new UpdateDesiredCourse(desiredCourseRepository, customerRepository);
        updateDesiredCourse.updateDesiredCourse(
                String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                desiredCourse
                );

        ///Assert
        DesiredCourseKey desiredCourseKey = new DesiredCourseKey(String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                String.valueOf(desiredCourse.getCourse().getIdCourse()));
        Optional<DesiredCourseModel> optionalDesiredCourseModelUpdated = desiredCourseRepository.findById(desiredCourseKey);

        assertTrue(optionalDesiredCourseModelUpdated.isPresent());

        DesiredCourseModel desiredCourseModelUpdated = optionalDesiredCourseModelUpdated.get();
        Customer customerThatDesiredCourse = DesiredCourseConverter.toCustomerWithEntity(desiredCourseModelUpdated);
        DesiredCourse desiredCourseUpdated = customerThatDesiredCourse.getDesiredCourses().iterator().next();

        assertEquals(desiredCourse.getDesireDate(), desiredCourseUpdated.getDesireDate());
        assertEquals(desiredCourse.getDesireDescription(), desiredCourseUpdated.getDesireDescription());

        Course courseCreated = desiredCourseUpdated.getCourse();
        assertEquals(course.getIdCourse(), courseCreated.getIdCourse());
        assertEquals(course.getName(), courseCreated.getName());
        assertEquals(course.getOriginalValue(), courseCreated.getOriginalValue());
        assertEquals(course.getTeacherResponsible().getIdTeacher(), courseCreated.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(), courseCreated.getTeacherResponsible().getName());

        assertEquals(customerWithADesiredCourse.getIdCustomer(), customerThatDesiredCourse.getIdCustomer());
        assertEquals(customerWithADesiredCourse.getFirstname(), customerThatDesiredCourse.getFirstname());
        assertEquals(customerWithADesiredCourse.getLastname(), customerThatDesiredCourse.getLastname());
        assertEquals(customerWithADesiredCourse.getPhone(), customerThatDesiredCourse.getPhone());
        assertEquals(customerWithADesiredCourse.getEmail(), customerThatDesiredCourse.getEmail());
        assertEquals(customerWithADesiredCourse.getLinkedIn(), customerThatDesiredCourse.getLinkedIn());
        assertEquals(customerWithADesiredCourse.getCompany(), customerThatDesiredCourse.getCompany());
        assertEquals(customerWithADesiredCourse.getPosition(), customerThatDesiredCourse.getPosition());
  }

    @Test
    @DisplayName("Given an invalid DesiredCourse domain, When it is tried to update this DesiredCourse, Then it will throw a ModelException")
    void Given_an_invalid_DesiredCourse_domain_When_it_is_tried_to_update_this_DesiredCourse_Then_it_will_throw_a_ModelException() {
        ///Arrange
        DesiredCourse desiredCourse = DomainUtils.generateDesiredCourse();

        ///Act
        UpdateDesiredCourse updateDesiredCourse = new UpdateDesiredCourse(desiredCourseRepository, customerRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> updateDesiredCourse.updateDesiredCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b",desiredCourse
                )
        );

        assertEquals("Customer not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
    
}