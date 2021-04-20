package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.UUID;

public class PostCustomerConverter {

    public static Customer toDomain (RequestPostCustomer body){
        Customer customer = new Customer();
        customer.setFirstname(body.getFirstname());
        customer.setLastname(body.getLastname());
        customer.setPhone(body.getPhone());
        customer.setLinkedIn(body.getLinkedIn());
        customer.setEmail(body.getEmail());
        customer.setCompany(body.getCompany());
        customer.setPosition(body.getPosition());

        return customer;
    }

    public static Customer toDomain (String idCustomer, RequestPostAcquiredCourseByCustomer body){
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(idCustomer));

        AcquiredCourse acquiredCourse = new AcquiredCourse();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        acquiredCourse.setAcquisitionDate(LocalDate.parse(body.getAcquisitionDate(),formatter));

        acquiredCourse.setValuePaid(NumberUtils.parseNumber(body.getValuePaid(),BigDecimal.class));

        Course course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        acquiredCourse.setCourse(course);

        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        return customer;
    }

    public static Customer toDomain (String idCustomer, RequestPostDesiredCourseByCustomer body){
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(idCustomer));


        DesiredCourse desiredCourse = new DesiredCourse();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        desiredCourse.setDesireDate(LocalDate.parse(body.getDesireDate(),formatter));
        desiredCourse.setDesireDescription(body.getDesireDescription());

        Course course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        desiredCourse.setCourse(course);

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

}
