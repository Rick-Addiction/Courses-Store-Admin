package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class UpdateDesiredCourseTest {

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
    public void Given_a_valid_DesiredCourse_stored_in_the_database_When_its_requested_to_update_the_DesiredCourse_Then_it_should_be_done_successfully() {

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
        DesiredCourse desiredCourseToUpdate = desiredCourse;
        desiredCourseToUpdate.setDesireDescription("He wants to increase his knowledge");

        UpdateDesiredCourse updateDesiredCourse = new UpdateDesiredCourse(desiredCourseRepository);
        updateDesiredCourse.updateDesiredCourse(customerWithADesiredCourse);

        ///Assert
        DesiredCourseKey desiredCourseKey = new DesiredCourseKey(String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                String.valueOf(desiredCourse.getCourse().getIdCourse()));
        Optional<DesiredCourseModel> optionalDesiredCourseModelUpdated = desiredCourseRepository.findById(desiredCourseKey);

        assertTrue(optionalDesiredCourseModelUpdated.isPresent());

        DesiredCourseModel desiredCourseModelUpdated = optionalDesiredCourseModelUpdated.get();
        Customer customerThatDesiredCourse = DesiredCourseConverter.toEntity(desiredCourseModelUpdated);
        DesiredCourse desiredCourseUpdated = customerThatDesiredCourse.getDesiredCourses().iterator().next();

        assertEquals(desiredCourse.getDesireDate(), desiredCourseUpdated.getDesireDate());
        assertEquals(desiredCourse.getDesireDescription(), desiredCourseUpdated.getDesireDescription());

        Course course = desiredCourse.getCourse();
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
    
}