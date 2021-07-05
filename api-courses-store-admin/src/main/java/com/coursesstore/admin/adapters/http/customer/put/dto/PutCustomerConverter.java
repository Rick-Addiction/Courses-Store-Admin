package com.coursesstore.admin.adapters.http.customer.put.dto;

import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PutCustomerConverter {

    private PutCustomerConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Customer toDomain (RequestPutCustomer body) {
        var customer = new Customer();
        customer.setIdCustomer(UUID.fromString(body.getIdCustomer()));
        customer.setFirstname(body.getFirstname());
        customer.setLastname(body.getLastname());
        customer.setLinkedIn(body.getLinkedIn());
        customer.setPhone(body.getPhone());
        customer.setEmail(body.getEmail());
        customer.setCompany(body.getCompany());
        customer.setPosition(body.getPosition());

        return customer;
    }

    public static AcquiredCourse toDomain (RequestPutAcquiredCourse body){
        var acquiredCourse = new AcquiredCourse();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        acquiredCourse.setAcquisitionDate(LocalDate.parse(body.getAcquisitionDate(),formatter));
        acquiredCourse.setValuePaid(NumberUtils.parseNumber(body.getValuePaid(), BigDecimal.class));

        var course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        acquiredCourse.setCourse(course);
        return acquiredCourse;
    }

    public static DesiredCourse toDomain (RequestPutDesiredCourse body){
        var desiredCourse = new DesiredCourse();

        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        desiredCourse.setDesireDate(LocalDate.parse(body.getDesireDate(),formatter));

        desiredCourse.setDesireDescription(body.getDesireDescription());

        var course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        desiredCourse.setCourse(course);

        return desiredCourse;
    }
}
