package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
class DesiredCourseConverterTest {

    @Test
    @DisplayName("Given a valid DesiredCourse entity, When the object is converted to a DesiredCourse model, Then It should be done successfully")
    void Given_a_valid_DesiredCourse_entity_When_the_object_is_converted_to_a_DesiredCourse_model_Then_It_should_be_done_successfully(){

        ///Arrange
        Customer customerWithADesiredCourse = DomainUtils.generateCustomerWithADesiredCourse();
        DesiredCourse desiredCourse = customerWithADesiredCourse.getDesiredCourses().iterator().next();

        ///Act
        DesiredCourseModel desiredCourseModel = DesiredCourseConverter.toModel(desiredCourse);

        ///Assert
        assertEquals(desiredCourseModel.getDesireDate(),desiredCourse.getDesireDate());
        assertEquals(desiredCourseModel.getDesireDescription(),desiredCourse.getDesireDescription());

        Course course = desiredCourse.getCourse();
        CourseModel courseModel = desiredCourseModel.getCourse();
        assertEquals(courseModel.getIdCourse(), String.valueOf(course.getIdCourse()));
        assertEquals(courseModel.getName(),course.getName());
        assertEquals(courseModel.getOriginalValue(),course.getOriginalValue());
        assertEquals(courseModel.getTeacherResponsible().getIdTeacher(),String.valueOf(course.getTeacherResponsible().getIdTeacher()));
        assertEquals(courseModel.getTeacherResponsible().getName(),course.getTeacherResponsible().getName());
    }

    @Test
    @DisplayName("Given a valid DesiredCourse model, When the object is converted to a DesiredCourse entity, Then It should be done successfully")
    void Given_a_valid_DesiredCourse_model_When_the_object_is_converted_to_a_DesiredCourse_entity_Then_It_should_be_done_successfully(){

        ///Arrange
        DesiredCourseModel desiredCourseModel = DomainUtils.generateDesiredCourseModel();

        ///Act
        Customer customerWithADesiredCourse = DesiredCourseConverter.toCustomerWithEntity(desiredCourseModel);
        DesiredCourse desiredCourse = customerWithADesiredCourse.getDesiredCourses().iterator().next();

        ///Assert
        assertEquals(desiredCourse.getDesireDate(),desiredCourseModel.getDesireDate());
        assertEquals(desiredCourse.getDesireDescription(),desiredCourseModel.getDesireDescription());

        Course course = desiredCourse.getCourse();
        CourseModel courseModel = desiredCourseModel.getCourse();
        assertEquals(String.valueOf(course.getIdCourse()), courseModel.getIdCourse());
        assertEquals(course.getName(),courseModel.getName());
        assertEquals(course.getOriginalValue(),courseModel.getOriginalValue());
        assertEquals(String.valueOf(course.getTeacherResponsible().getIdTeacher()),courseModel.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(),courseModel.getTeacherResponsible().getName());

        CustomerModel customerModel = desiredCourseModel.getCustomer();
        assertEquals(String.valueOf(customerWithADesiredCourse.getIdCustomer()), customerModel.getIdCustomer());
        assertEquals(customerWithADesiredCourse.getFirstname(),customerModel.getFirstname());
        assertEquals(customerWithADesiredCourse.getLastname(),customerModel.getLastname());
        assertEquals(customerWithADesiredCourse.getPhone(),customerModel.getPhone());
        assertEquals(customerWithADesiredCourse.getEmail(),customerModel.getEmail());
        assertEquals(customerWithADesiredCourse.getLinkedIn(),customerModel.getLinkedIn());
        assertEquals(customerWithADesiredCourse.getCompany(),customerModel.getCompany());
        assertEquals(customerWithADesiredCourse.getPosition(),customerModel.getPosition());
    }

}