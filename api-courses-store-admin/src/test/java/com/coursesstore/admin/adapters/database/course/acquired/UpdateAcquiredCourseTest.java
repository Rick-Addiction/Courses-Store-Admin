package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class UpdateAcquiredCourseTest {

    @Autowired
    private AcquiredCourseRepository acquiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid AcquiredCourse stored in the database, When its requested to update the AcquiredCourse, Then it should be done successfully")
    public void Given_a_valid_AcquiredCourse_stored_in_the_database_When_its_requested_to_update_the_AcquiredCourse_Then_it_should_be_done_successfully() {

        ///Arrange
        Customer customerThatAcquiredACourse = DomainUtils.generateCustomerWithAnAcquiredCourse();
        AcquiredCourse acquiredCourse = customerThatAcquiredACourse.getAcquiredCourses().iterator().next();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(acquiredCourse.getCourse().getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(acquiredCourse.getCourse());

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customerThatAcquiredACourse);

        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository, customerRepository);
        addAcquiredCourse.addNewAcquiredCourseByCustomer(customerThatAcquiredACourse);

        ///Act
        AcquiredCourse acquiredCourseToUpdate = acquiredCourse;
        acquiredCourseToUpdate.setValuePaid(BigDecimal.valueOf(10L).setScale(2));

        UpdateAcquiredCourse updateAcquiredCourse = new UpdateAcquiredCourse(acquiredCourseRepository);
        updateAcquiredCourse.updateAcquiredCourse(acquiredCourseToUpdate);

        ///Assert
        Optional<AcquiredCourseModel> optionalAcquiredCourseModelUpdated = acquiredCourseRepository.findByIdAcquiredCourse(String.valueOf(acquiredCourse.getIdAcquiredCourse()));

        assertTrue(optionalAcquiredCourseModelUpdated.isPresent());

        AcquiredCourseModel acquiredCourseModelUpdated = optionalAcquiredCourseModelUpdated.get();
        Customer customerThatUpdatedTheAcquiredACourse = AcquiredCourseConverter.toEntity(acquiredCourseModelUpdated);
        AcquiredCourse acquiredCourseUpdated = customerThatUpdatedTheAcquiredACourse.getAcquiredCourses().iterator().next();

        assertEquals(acquiredCourse.getIdAcquiredCourse(), acquiredCourseUpdated.getIdAcquiredCourse());
        assertEquals(acquiredCourse.getAcquisitionDate(), acquiredCourseUpdated.getAcquisitionDate());
        assertEquals(acquiredCourse.getValuePaid(), acquiredCourseUpdated.getValuePaid());


        Course course = acquiredCourse.getCourse();
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
    
}