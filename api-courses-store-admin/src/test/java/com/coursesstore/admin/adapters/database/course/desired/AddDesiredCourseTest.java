package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class AddDesiredCourseTest {
    @Autowired
    private DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid DesiredCourse domain object, When the desiredCourse is not in the database, Then create a new desiredCourse")
    void Given_a_valid_DesiredCourse_domain_object_When_the_desiredCourse_is_not_in_the_database_Then_create_a_new_desiredCourse() {

        ///Arrange
        Customer customerWithADesiredCourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        DesiredCourse desiredCourse = DomainUtils.generateDesiredCourse(course);

        ///Act
        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,customerRepository, courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(
                String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                desiredCourse
        );

        ///Assert

        DesiredCourseKey desiredCourseKey = new DesiredCourseKey(String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                String.valueOf(course.getIdCourse()));
        Optional<DesiredCourseModel> optionalDesiredCourseModel = desiredCourseRepository.findById(desiredCourseKey);

        assertTrue(optionalDesiredCourseModel.isPresent());

        DesiredCourseModel desiredCourseModel = optionalDesiredCourseModel.get();
        Customer customerThatDesiredACourse = DesiredCourseConverter.toCustomerWithEntity(desiredCourseModel);
        DesiredCourse desiredCourseAdded = customerThatDesiredACourse.getDesiredCourses().iterator().next();

        assertEquals(desiredCourse.getDesireDate(), desiredCourseAdded.getDesireDate());
        assertEquals(desiredCourse.getDesireDescription(), desiredCourseAdded.getDesireDescription());


        Course courseCreated = desiredCourseAdded.getCourse();
        assertEquals(course.getIdCourse(), courseCreated.getIdCourse());
        assertEquals(course.getName(), courseCreated.getName());
        assertEquals(course.getOriginalValue(), courseCreated.getOriginalValue());
        assertEquals(course.getTeacherResponsible().getIdTeacher(), courseCreated.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(), courseCreated.getTeacherResponsible().getName());


        assertEquals(customerWithADesiredCourse.getIdCustomer(), customerThatDesiredACourse.getIdCustomer());
        assertEquals(customerWithADesiredCourse.getFirstname(), customerThatDesiredACourse.getFirstname());
        assertEquals(customerWithADesiredCourse.getLastname(), customerThatDesiredACourse.getLastname());
        assertEquals(customerWithADesiredCourse.getPhone(), customerThatDesiredACourse.getPhone());
        assertEquals(customerWithADesiredCourse.getEmail(), customerThatDesiredACourse.getEmail());
        assertEquals(customerWithADesiredCourse.getLinkedIn(), customerThatDesiredACourse.getLinkedIn());
        assertEquals(customerWithADesiredCourse.getCompany(), customerThatDesiredACourse.getCompany());
        assertEquals(customerWithADesiredCourse.getPosition(), customerThatDesiredACourse.getPosition());

    }
}