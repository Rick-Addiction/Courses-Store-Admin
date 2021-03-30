package com.coursesstore.admin.adapters.http.course.post.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
