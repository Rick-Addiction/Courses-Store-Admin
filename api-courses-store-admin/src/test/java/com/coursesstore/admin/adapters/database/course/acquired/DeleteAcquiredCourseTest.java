package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteAcquiredCourseTest {

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
    public void Given_a_AcquiredCourse_stored_in_the_database_When_its_requested_to_delete_the_AcquiredCourse_Then_it_should_be_done_successfully () {

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


}