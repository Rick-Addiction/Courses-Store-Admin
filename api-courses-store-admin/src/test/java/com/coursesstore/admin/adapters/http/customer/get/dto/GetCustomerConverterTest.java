package com.coursesstore.admin.adapters.http.customer.get.dto;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class GetCustomerConverterTest {

    @Test
    @DisplayName("Given a valid List of Customer entities domain, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_valid_List_of_Customer_entities_domain_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {

        ///Arrange
        var customers = List.of(
                DomainUtils.generateCustomer(),
                DomainUtils.generateCustomer()
        );

        ///Act
        ResponseGetCustomer responseGetCustomer = GetCustomerConverter.toResponseGetCustomer(customers);

        ///Assert
        assertEquals(responseGetCustomer.getCustomers().get(0).getIdCustomer(), String.valueOf(customers.get(0).getIdCustomer()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getFirstname(), String.valueOf(customers.get(0).getFirstname()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getLastname(), String.valueOf(customers.get(0).getLastname()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getEmail(), String.valueOf(customers.get(0).getEmail()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getPhone(), String.valueOf(customers.get(0).getPhone()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getCompany(), String.valueOf(customers.get(0).getCompany()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getPosition(), String.valueOf(customers.get(0).getPosition()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getLinkedIn(), String.valueOf(customers.get(0).getLinkedIn()));

        assertEquals(responseGetCustomer.getCustomers().get(1).getIdCustomer(), String.valueOf(customers.get(1).getIdCustomer()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getFirstname(), String.valueOf(customers.get(1).getFirstname()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getLastname(), String.valueOf(customers.get(1).getLastname()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getEmail(), String.valueOf(customers.get(1).getEmail()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getPhone(), String.valueOf(customers.get(1).getPhone()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getCompany(), String.valueOf(customers.get(1).getCompany()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getPosition(), String.valueOf(customers.get(1).getPosition()));
        assertEquals(responseGetCustomer.getCustomers().get(1).getLinkedIn(), String.valueOf(customers.get(1).getLinkedIn()));

    }

    @Test
    @DisplayName("Given a empty List of Customers, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_empty_List_of_Customers_by_Customer_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var customers = new ArrayList<Customer>();

        ///Act
        ResponseGetCustomer responseGetCustomers = GetCustomerConverter.toResponseGetCustomer(customers);

        ///Assert
        assertNull(responseGetCustomers.getCustomers());

    }

    @Test
    @DisplayName("Given a valid Customer object domain, When these object is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_valid_Customer_object_domain_When_these_object_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {

        ///Arrange
        var customer = DomainUtils.generateCustomer();

        ///Act
        ResponseGetCustomer responseGetCustomer = GetCustomerConverter.toResponseGetCustomer(customer);

        ///Assert
        assertEquals(responseGetCustomer.getCustomers().get(0).getIdCustomer(), String.valueOf(customer.getIdCustomer()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getFirstname(), String.valueOf(customer.getFirstname()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getLastname(), String.valueOf(customer.getLastname()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getEmail(), String.valueOf(customer.getEmail()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getPhone(), String.valueOf(customer.getPhone()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getCompany(), String.valueOf(customer.getCompany()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getPosition(), String.valueOf(customer.getPosition()));
        assertEquals(responseGetCustomer.getCustomers().get(0).getLinkedIn(), String.valueOf(customer.getLinkedIn()));
    }

        @Test
    @DisplayName("Given a valid List of Acquired Courses by Customer and Not Acquired Courses by Customer, When these objects is converted to a ResponseGetAcquiredCoursesByCustomer, Then It should be done successfully")
    void Given_a_valid_List_of_Acquired_Courses_by_Customer_and_Not_Acquired_Courses_by_Customer_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {

        ///Arrange
        var listAcquiredCoursesByCustomer = List.of(
                DomainUtils.generateAcquiredCourse(),
                DomainUtils.generateAcquiredCourse()
        );

        var listNotAcquiredCoursesByCustomer = List.of(
                DomainUtils.generateCourse(),
                DomainUtils.generateCourse()
        );

        ///Act
        ResponseGetAcquiredCoursesByCustomer responseGetAcquiredCoursesByCustomer = GetCustomerConverter
                .toResponseGetAcquiredCoursesByCustomer(
                        listAcquiredCoursesByCustomer,
                        listNotAcquiredCoursesByCustomer
                );

        ///Assert
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(0).getIdAcquiredCourse(),
                String.valueOf(listAcquiredCoursesByCustomer.get(0).getCourse().getIdCourse()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(0).getCourseName(),
                String.valueOf(listAcquiredCoursesByCustomer.get(0).getCourse().getName()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(0).getAcquisitionDate(),
                String.valueOf(listAcquiredCoursesByCustomer.get(0).getAcquisitionDate()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(0).getValuePaid(),
                String.valueOf(listAcquiredCoursesByCustomer.get(0).getValuePaid()));

        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(1).getIdAcquiredCourse(),
                String.valueOf(listAcquiredCoursesByCustomer.get(1).getCourse().getIdCourse()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(1).getCourseName(),
                String.valueOf(listAcquiredCoursesByCustomer.get(1).getCourse().getName()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(1).getAcquisitionDate(),
                String.valueOf(listAcquiredCoursesByCustomer.get(1).getAcquisitionDate()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getAcquiredCourses().get(1).getValuePaid(),
                String.valueOf(listAcquiredCoursesByCustomer.get(1).getValuePaid()));

        assertEquals(responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses().get(0).getIdCourse(),
                String.valueOf(listNotAcquiredCoursesByCustomer.get(0).getIdCourse()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses().get(0).getName(),
                String.valueOf(listNotAcquiredCoursesByCustomer.get(0).getName()));

        assertEquals(responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses().get(1).getIdCourse(),
                String.valueOf(listNotAcquiredCoursesByCustomer.get(1).getIdCourse()));
        assertEquals(responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses().get(1).getName(),
                String.valueOf(listNotAcquiredCoursesByCustomer.get(1).getName()));
    }

    @Test
    @DisplayName("Given a empty List of Acquired Courses by Customer and empty list of Not Acquired Courses by Customer, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_empty_List_of_Acquired_Courses_by_Customer_and_empty_list_of_Not_Acquired_Courses_by_Customer_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var listAcquiredCoursesByCustomer = new ArrayList<AcquiredCourse>();

        var listNotAcquiredCoursesByCustomer = new ArrayList<Course>();

        ///Act
        ResponseGetAcquiredCoursesByCustomer responseGetAcquiredCoursesByCustomer = GetCustomerConverter
                .toResponseGetAcquiredCoursesByCustomer(
                        listAcquiredCoursesByCustomer,
                        listNotAcquiredCoursesByCustomer
                );

        ///Assert
        assertNull(responseGetAcquiredCoursesByCustomer.getAcquiredCourses());
        assertNull(responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses());

    }

    @Test
    @DisplayName("Given a valid List of Desired Courses by Customer and Not Desired Courses by Customer, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_valid_List_of_Desired_Courses_by_Customer_and_Not_Desired_Courses_by_Customer_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var listDesiredCoursesByCustomer = List.of(
                DomainUtils.generateDesiredCourse(),
                DomainUtils.generateDesiredCourse()
        );

        var listNotDesiredCoursesByCustomer = List.of(
                DomainUtils.generateCourse(),
                DomainUtils.generateCourse()
        );

        ///Act
        ResponseGetDesiredCoursesByCustomer responseGetDesiredCoursesByCustomer = GetCustomerConverter
                .toResponseGetDesiredCoursesByCustomer(
                        listDesiredCoursesByCustomer,
                        listNotDesiredCoursesByCustomer
                );

        ///Assert
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(0).getIdDesiredCourse(),
                String.valueOf(listDesiredCoursesByCustomer.get(0).getCourse().getIdCourse()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(0).getCourseName(),
                String.valueOf(listDesiredCoursesByCustomer.get(0).getCourse().getName()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(0).getDesireDate(),
                String.valueOf(listDesiredCoursesByCustomer.get(0).getDesireDate()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(0).getDesireDescription(),
                String.valueOf(listDesiredCoursesByCustomer.get(0).getDesireDescription()));

        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(1).getIdDesiredCourse(),
                String.valueOf(listDesiredCoursesByCustomer.get(1).getCourse().getIdCourse()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(1).getCourseName(),
                String.valueOf(listDesiredCoursesByCustomer.get(1).getCourse().getName()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(1).getDesireDate(),
                String.valueOf(listDesiredCoursesByCustomer.get(1).getDesireDate()));
        assertEquals(responseGetDesiredCoursesByCustomer.getDesiredCourses().get(1).getDesireDescription(),
                String.valueOf(listDesiredCoursesByCustomer.get(1).getDesireDescription()));

        assertEquals(responseGetDesiredCoursesByCustomer.getNotDesiredCourses().get(0).getIdCourse(),
                String.valueOf(listNotDesiredCoursesByCustomer.get(0).getIdCourse()));
        assertEquals(responseGetDesiredCoursesByCustomer.getNotDesiredCourses().get(0).getName(),
                String.valueOf(listNotDesiredCoursesByCustomer.get(0).getName()));

        assertEquals(responseGetDesiredCoursesByCustomer.getNotDesiredCourses().get(1).getIdCourse(),
                String.valueOf(listNotDesiredCoursesByCustomer.get(1).getIdCourse()));
        assertEquals(responseGetDesiredCoursesByCustomer.getNotDesiredCourses().get(1).getName(),
                String.valueOf(listNotDesiredCoursesByCustomer.get(1).getName()));

    }

    @Test
    @DisplayName("Given a empty List of Desired Courses by Customer and empty list of Not Desired Courses by Customer, When these objects is converted to a ResponseGetCustomer, Then It should be done successfully")
    void Given_a_empty_List_of_Desired_Courses_by_Customer_and_empty_list_of_Not_Desired_Courses_by_Customer_When_these_objects_is_converted_to_a_ResponseGetCustomer_Then_It_should_be_done_successfully() {
        ///Arrange
        var listDesiredCoursesByCustomer = new ArrayList<DesiredCourse>();

        var listNotDesiredCoursesByCustomer = new ArrayList<Course>();

        ///Act
        ResponseGetDesiredCoursesByCustomer responseGetDesiredCoursesByCustomer = GetCustomerConverter
                .toResponseGetDesiredCoursesByCustomer(
                        listDesiredCoursesByCustomer,
                        listNotDesiredCoursesByCustomer
                );

        ///Assert
        assertNull(responseGetDesiredCoursesByCustomer.getDesiredCourses());
        assertNull(responseGetDesiredCoursesByCustomer.getNotDesiredCourses());

    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor constructor = GetCustomerConverter.class.getDeclaredConstructor();
        assertTrue("Constructor is not private", Modifier.isPrivate(constructor.getModifiers()));
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> {
                    constructor.setAccessible(true);
                    try{
                    constructor.newInstance();
                    }
                    catch (InvocationTargetException e) {
                        throw (IllegalStateException) e.getTargetException();
                    }

                });

        assertEquals("Utility class",exception.getMessage());
    }

}