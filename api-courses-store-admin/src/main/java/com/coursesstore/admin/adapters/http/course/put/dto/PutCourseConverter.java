package com.coursesstore.admin.adapters.http.course.put.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.util.NumberUtils;

import java.math.BigDecimal;
import java.util.UUID;

public class PutCourseConverter {

    private PutCourseConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Course toDomain (RequestPutCourse body){
        var course = new Course();

        course.setIdCourse(UUID.fromString(body.getIdCourse()));
        course.setName(body.getName());
        course.setOriginalValue(NumberUtils.parseNumber(body.getOriginalValue(),BigDecimal.class));
        var teacherResponsible = new Teacher();
        teacherResponsible.setIdTeacher(UUID.fromString(body.getIdTeacherResponsible()));
        course.setTeacherResponsible(teacherResponsible);

        return course;
    }
}
