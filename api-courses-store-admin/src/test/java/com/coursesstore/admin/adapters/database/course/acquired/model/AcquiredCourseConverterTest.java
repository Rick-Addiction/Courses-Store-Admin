package com.coursesstore.admin.adapters.database.course.acquired.model;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
public class AcquiredCourseConverterTest {

    @Test
    @DisplayName("Given a valid AcquiredCourse entity, When the object is converted to a AcquiredCourse model, Then It should be done successfully")
    public void Given_a_valid_AcquiredCourse_entity_When_the_object_is_converted_to_a_AcquiredCourse_model_Then_It_should_be_done_successfully(){

        ///Arrange
        Customer customerThatAcquiredACourse = DomainUtils.generateCustomerWithAnAcquiredCourse();
        AcquiredCourse acquiredCourse = customerThatAcquiredACourse.getAcquiredCourses().iterator().next();

        ///Act
        AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(acquiredCourse);

        ///Assert
        assertEquals(acquiredCourseModel.getAcquisitionDate(),acquiredCourse.getAcquisitionDate());
        assertEquals(acquiredCourseModel.getValuePaid(),acquiredCourse.getValuePaid());

        Course course = acquiredCourse.getCourse();
        CourseModel courseModel = acquiredCourseModel.getCourse();
        assertEquals(courseModel.getIdCourse(), String.valueOf(course.getIdCourse()));
        assertEquals(courseModel.getName(),course.getName());
        assertEquals(courseModel.getOriginalValue(),course.getOriginalValue());
        assertEquals(courseModel.getTeacherResponsible().getIdTeacher(),String.valueOf(course.getTeacherResponsible().getIdTeacher()));
        assertEquals(courseModel.getTeacherResponsible().getName(),course.getTeacherResponsible().getName());

    }

    @Test
    @DisplayName("Given a valid AcquiredCourse model, When the object is converted to a AcquiredCourse entity, Then It should be done successfully")
    public void Given_a_valid_AcquiredCourse_model_When_the_object_is_converted_to_a_AcquiredCourse_entity_Then_It_should_be_done_successfully(){

        ///Arrange
        AcquiredCourseModel acquiredCourseModel = DomainUtils.generateAcquiredCourseModel();

        ///Act
        Customer customerThatAcquiredACourse = AcquiredCourseConverter.toEntity(acquiredCourseModel);
        AcquiredCourse acquiredCourse = customerThatAcquiredACourse.getAcquiredCourses().iterator().next();

        ///Assert
        assertEquals(acquiredCourse.getAcquisitionDate(),acquiredCourseModel.getAcquisitionDate());
        assertEquals(acquiredCourse.getValuePaid(),acquiredCourseModel.getValuePaid());

        Course course = acquiredCourse.getCourse();
        CourseModel courseModel = acquiredCourseModel.getCourse();
        assertEquals(String.valueOf(course.getIdCourse()), courseModel.getIdCourse());
        assertEquals(course.getName(),courseModel.getName());
        assertEquals(course.getOriginalValue(),courseModel.getOriginalValue());
        assertEquals(String.valueOf(course.getTeacherResponsible().getIdTeacher()),courseModel.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(),courseModel.getTeacherResponsible().getName());

        CustomerModel customerModel = acquiredCourseModel.getCustomer();
        assertEquals(String.valueOf(customerThatAcquiredACourse.getIdCustomer()), customerModel.getIdCustomer());
        assertEquals(customerThatAcquiredACourse.getFirstname(),customerModel.getFirstname());
        assertEquals(customerThatAcquiredACourse.getLastname(),customerModel.getLastname());
        assertEquals(customerThatAcquiredACourse.getPhone(),customerModel.getPhone());
        assertEquals(customerThatAcquiredACourse.getEmail(),customerModel.getEmail());
        assertEquals(customerThatAcquiredACourse.getLinkedIn(),customerModel.getLinkedIn());
        assertEquals(customerThatAcquiredACourse.getCompany(),customerModel.getCompany());
        assertEquals(customerThatAcquiredACourse.getPosition(),customerModel.getPosition());
    }
    
}