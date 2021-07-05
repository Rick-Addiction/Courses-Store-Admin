package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.DesiredCourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.UpdateDesiredCourse;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.customer.FindCustomerById;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class UpdateAcquiredCourseTest {

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

    @Autowired
    private FindCustomerById findCustomerById;

    @Test
    @DisplayName("Given a valid AcquiredCourse stored in the database, When its requested to update the AcquiredCourse, Then it should be done successfully")
    void Given_a_valid_AcquiredCourse_stored_in_the_database_When_its_requested_to_update_the_AcquiredCourse_Then_it_should_be_done_successfully() {

        ///Arrange
        Customer customerThatAcquiredACourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        AcquiredCourse acquiredCourse = DomainUtils.generateAcquiredCourse(course);


        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository, customerRepository, courseRepository);
        addAcquiredCourse.addNewAcquiredCourseByCustomer(
                String.valueOf(customerThatAcquiredACourse.getIdCustomer()),
                acquiredCourse);

        ///Act
        AcquiredCourse acquiredCourseToUpdate = acquiredCourse;
        acquiredCourseToUpdate.setValuePaid(BigDecimal.valueOf(10L).setScale(2));

        UpdateAcquiredCourse updateAcquiredCourse = new UpdateAcquiredCourse(acquiredCourseRepository,customerRepository);
        updateAcquiredCourse.updateAcquiredCourse(
                String.valueOf(customerThatAcquiredACourse.getIdCustomer()),
                acquiredCourse);

        ///Assert
        AcquiredCourseKey acquiredCourseKey = new AcquiredCourseKey(String.valueOf(customerThatAcquiredACourse.getIdCustomer()),
                String.valueOf(acquiredCourse.getCourse().getIdCourse()));
        Optional<AcquiredCourseModel> optionalAcquiredCourseModelUpdated = acquiredCourseRepository.findById(acquiredCourseKey);

        assertTrue(optionalAcquiredCourseModelUpdated.isPresent());

        AcquiredCourseModel acquiredCourseModelUpdated = optionalAcquiredCourseModelUpdated.get();
        Customer customerThatUpdatedTheAcquiredACourse = AcquiredCourseConverter.toCustomerWithEntity(acquiredCourseModelUpdated);
        AcquiredCourse acquiredCourseUpdated = customerThatUpdatedTheAcquiredACourse.getAcquiredCourses().iterator().next();

        assertEquals(acquiredCourse.getAcquisitionDate(), acquiredCourseUpdated.getAcquisitionDate());
        assertEquals(acquiredCourse.getValuePaid(), acquiredCourseUpdated.getValuePaid());


        Course courseCreated = acquiredCourseUpdated.getCourse();
        assertEquals(course.getIdCourse(), courseCreated.getIdCourse());
        assertEquals(course.getName(), courseCreated.getName());
        assertEquals(course.getOriginalValue(), courseCreated.getOriginalValue());
        assertEquals(course.getTeacherResponsible().getIdTeacher(), courseCreated.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(), courseCreated.getTeacherResponsible().getName());

        assertEquals(customerThatAcquiredACourse.getIdCustomer(), customerThatUpdatedTheAcquiredACourse.getIdCustomer());
        assertEquals(customerThatAcquiredACourse.getFirstname(), customerThatUpdatedTheAcquiredACourse.getFirstname());
        assertEquals(customerThatAcquiredACourse.getLastname(), customerThatUpdatedTheAcquiredACourse.getLastname());
        assertEquals(customerThatAcquiredACourse.getPhone(), customerThatUpdatedTheAcquiredACourse.getPhone());
        assertEquals(customerThatAcquiredACourse.getEmail(), customerThatUpdatedTheAcquiredACourse.getEmail());
        assertEquals(customerThatAcquiredACourse.getLinkedIn(), customerThatUpdatedTheAcquiredACourse.getLinkedIn());
        assertEquals(customerThatAcquiredACourse.getCompany(), customerThatUpdatedTheAcquiredACourse.getCompany());
        assertEquals(customerThatAcquiredACourse.getPosition(), customerThatUpdatedTheAcquiredACourse.getPosition());
}

    @Test
    @DisplayName("Given an invalid AcquiredCourse domain, When it is tried to update this AcquiredCourse, Then it will throw a ModelException")
    void Given_an_invalid_AcquiredCourse_domain_When_it_is_tried_to_update_this_AcquiredCourse_Then_it_will_throw_a_ModelException() {
        ///Arrange
        AcquiredCourse acquiredCourse = DomainUtils.generateAcquiredCourse();

        ///Act
        UpdateAcquiredCourse updateAcquiredCourse = new UpdateAcquiredCourse(acquiredCourseRepository, customerRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> updateAcquiredCourse.updateAcquiredCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b",acquiredCourse
                )
        );

        assertEquals("Customer not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
    
}