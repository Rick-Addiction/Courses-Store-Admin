package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteDesiredCourseTest {

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
    public void Given_a_DesiredCourse_stored_in_the_database_When_its_requested_to_delete_the_DesiredCourse_Then_it_should_be_done_successfully () {

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


}