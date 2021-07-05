package com.coursesstore.admin.adapters.http.course.post.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class PostCourseConverter {

    private PostCourseConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Course toDomain (RequestPostCourse body){
        var course = new Course();

        course.setName(body.getName());
        course.setOriginalValue(BigDecimal.valueOf(Double.parseDouble(body.getOriginalValue())).setScale(2,RoundingMode.HALF_EVEN));
        var teacherResponsible = new Teacher();
        teacherResponsible.setIdTeacher(UUID.fromString(body.getIdTeacherResponsible()));
        course.setTeacherResponsible(teacherResponsible);

        return course;
    }
}
