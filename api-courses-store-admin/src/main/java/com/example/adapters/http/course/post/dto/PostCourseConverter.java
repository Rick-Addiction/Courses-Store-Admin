package com.example.adapters.http.course.post.dto;

import com.example.core.domain.course.Course;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.domain.customer.Customer;
import com.example.core.domain.teacher.Teacher;

import javax.swing.text.DateFormatter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class PostCourseConverter {

    public static Course toDomain (RequestPostCourse body){
        Course course = new Course();

        course.setName(body.getName());
        course.setOriginalValue(BigDecimal.valueOf(Long.parseLong(body.getOriginalValue())));
        Teacher teacherResponsible = new Teacher();
        teacherResponsible.setIdTeacher(UUID.fromString(body.getIdTeacherResponsible()));
        course.setTeacherResponsible(teacherResponsible);

        return course;
    }

    public static AcquiredCourse toDomain (RequestPostAcquiredCourse body){
        AcquiredCourse acquiredCourse = new AcquiredCourse();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            acquiredCourse.setAcquisitionDate(dateFormat.parse(body.getAcquisitionDate()));
        } catch (ParseException e) {
            e.printStackTrace();//TODO Set log and Exception
        }

        acquiredCourse.setValuePaid(BigDecimal.valueOf(Long.parseLong(body.getValuePaid())));

        Course course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        acquiredCourse.setCourse(course);

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(body.getIdCustomer()));
        acquiredCourse.setCustomer(customer);

        return acquiredCourse;
    }

    public static DesiredCourse toDomain (RequestPostDesiredCourse body){
        DesiredCourse desiredCourse = new DesiredCourse();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            desiredCourse.setDesireDate(dateFormat.parse(body.getDesireDate()));
        } catch (ParseException e) {
            e.printStackTrace();//TODO Set log and Exception
        }

        desiredCourse.setDesireDescription(body.getDesireDescription());

        Course course = new Course();
        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        desiredCourse.setCourse(course);

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.fromString(body.getIdCustomer()));
        desiredCourse.setCustomer(customer);

        return desiredCourse;
    }

}
