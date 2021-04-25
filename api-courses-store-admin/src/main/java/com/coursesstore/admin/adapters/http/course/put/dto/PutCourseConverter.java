package com.coursesstore.admin.adapters.http.course.put.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class PutCourseConverter {

    public static Course toDomain (RequestPutCourse body){
        Course course = new Course();

        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        course.setName(body.getName());
        course.setOriginalValue(NumberUtils.parseNumber(body.getOriginalValue(),BigDecimal.class));
        Teacher teacherResponsible = new Teacher();
        teacherResponsible.setIdTeacher(UUID.fromString(body.getIdTeacherResponsible()));
        course.setTeacherResponsible(teacherResponsible);

        return course;
    }
}
