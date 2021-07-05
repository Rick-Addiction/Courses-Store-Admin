package com.coursesstore.admin.adapters.http.customer.put.dto;

import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.adapters.http.customer.get.dto.GetCustomerConverter;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertTrue;

class PutCustomerConverterTest {

    @Test
    @DisplayName("Given a valid RequestPutCustomer dto, When this object is converted to a Customer, Then It should be done successfully")
    void Given_a_valid_RequestPutCustomer_dto_When_this_object_is_converted_to_a_Customer_Then_It_should_be_done_successfully() {

        RequestPutCustomer requestPutCustomerExpected = DomainUtils.generateRequestPutCustomer(String.valueOf(UUID.randomUUID()));

        Customer customerActual = PutCustomerConverter.toDomain(requestPutCustomerExpected);

        assertEquals(requestPutCustomerExpected.getIdCustomer(),String.valueOf(customerActual.getIdCustomer()));
        assertEquals(requestPutCustomerExpected.getCompany(),customerActual.getCompany());
        assertEquals(requestPutCustomerExpected.getEmail(),customerActual.getEmail());
        assertEquals(requestPutCustomerExpected.getFirstname(),customerActual.getFirstname());
        assertEquals(requestPutCustomerExpected.getLastname(),customerActual.getLastname());
        assertEquals(requestPutCustomerExpected.getLinkedIn(),customerActual.getLinkedIn());
        assertEquals(requestPutCustomerExpected.getPhone(),customerActual.getPhone());
        assertEquals(requestPutCustomerExpected.getPosition(),customerActual.getPosition());
    }

    @Test
    @DisplayName("Given a valid RequestPutAcquiredCourse dto, When this object is converted to a AcquiredCourse, Then It should be done successfully")
    void Given_a_valid_RequestPutAcquiredCourse_dto_When_this_object_is_converted_to_a_AcquiredCourse_Then_It_should_be_done_successfully() {

        RequestPutAcquiredCourse requestPutAcquiredCourseExpected = DomainUtils.generateRequestPutAcquiredCourse();

        AcquiredCourse acquiredCourseActual = PutCustomerConverter.toDomain(requestPutAcquiredCourseExpected);

        assertEquals(requestPutAcquiredCourseExpected.getIdCourse(),String.valueOf(acquiredCourseActual.getCourse().getIdCourse()));
        assertEquals(requestPutAcquiredCourseExpected.getAcquisitionDate(),String.valueOf(acquiredCourseActual.getAcquisitionDate()));
        assertEquals(requestPutAcquiredCourseExpected.getValuePaid(),String.valueOf(acquiredCourseActual.getValuePaid()));
    }

    @Test
    @DisplayName("Given a valid RequestPutDesiredCourse dto, When this object is converted to a DesiredCourse, Then It should be done successfully")
    void Given_a_valid_RequestPutDesiredCourse_dto_When_this_object_is_converted_to_a_DesiredCourse_Then_It_should_be_done_successfully() {

        RequestPutDesiredCourse requestPutDesiredCourseExpected = DomainUtils.generateRequestPutDesiredCourse();

        DesiredCourse desiredCourseActual = PutCustomerConverter.toDomain(requestPutDesiredCourseExpected);

        assertEquals(requestPutDesiredCourseExpected.getIdCourse(),String.valueOf(desiredCourseActual.getCourse().getIdCourse()));
        assertEquals(requestPutDesiredCourseExpected.getDesireDate(),String.valueOf(desiredCourseActual.getDesireDate()));
        assertEquals(requestPutDesiredCourseExpected.getDesireDescription(),String.valueOf(desiredCourseActual.getDesireDescription()));
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        var constructor = PutCustomerConverter.class.getDeclaredConstructor();
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