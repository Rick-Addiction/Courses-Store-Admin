package com.coursesstore.admin.adapters.http.teacher.put.dto;

import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.util.UUID;

public class PutTeacherConverter {

    private PutTeacherConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static Teacher toDomain (RequestPutTeacher body) {
        var teacher = new Teacher();
        teacher.setIdTeacher(UUID.fromString(body.getIdTeacher()));
        teacher.setName(body.getName());

        return teacher;
    }
}
