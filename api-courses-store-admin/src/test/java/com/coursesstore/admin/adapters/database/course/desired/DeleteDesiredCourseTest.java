package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.DeleteAcquiredCourse;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class DeleteDesiredCourseTest {

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
    void Given_a_DesiredCourse_stored_in_the_database_When_its_requested_to_delete_the_DesiredCourse_Then_it_should_be_done_successfully () {

        ///Arrange
        Customer customerWithADesiredCourse = AdapterUtils.registerANewCustomer();
        Teacher teacher = AdapterUtils.registerANewTeacher();
        Course course = AdapterUtils.registerANewCourse(teacher);

        String idCostumer = String.valueOf(customerWithADesiredCourse.getIdCustomer());

        DesiredCourse desiredCourse = AdapterUtils.registerANewDesiredCourse(
                idCostumer,
                course);

        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,customerRepository,courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(idCostumer,desiredCourse);

        ///Act
        DeleteDesiredCourse deleteDesiredCourse = new DeleteDesiredCourse(desiredCourseRepository);
        deleteDesiredCourse.deleteDesiredCourse(
                String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                String.valueOf(desiredCourse.getCourse().getIdCourse()));


        ///Assert
        DesiredCourseKey desiredCourseKey = new DesiredCourseKey(
                String.valueOf(customerWithADesiredCourse.getIdCustomer()),
                String.valueOf(desiredCourse.getCourse().getIdCourse()));
        Optional<DesiredCourseModel> optionalDeletedDesiredCourseModel = desiredCourseRepository.findById(desiredCourseKey);
        assertTrue(optionalDeletedDesiredCourseModel.isEmpty());
    }

    @Test
    @DisplayName("Given an invalid DesiredCourse domain, When it is tried to delete this DesiredCourse, Then it will throw a ModelException")
    void Given_an_invalid_DesiredCourse_domain_When_it_is_tried_to_delete_this_DesiredCourse_Then_it_will_throw_a_ModelException() {
        ///Act
        DeleteDesiredCourse deleteDesiredCourse = new DeleteDesiredCourse(desiredCourseRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> deleteDesiredCourse.deleteDesiredCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b",
                        "38885619-b397-4dd1-b029-61fc35f55427"
                )
        );

        assertEquals("Desired Course not found -  Customer d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b, Course 38885619-b397-4dd1-b029-61fc35f55427!",exception.getMessage());
    }


}