package com.coursesstore.admin.adapters.http.teacher.put.dto;

import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.util.UUID;

public class PutTeacherConverter {
    //TODO Unit Test
    public static Teacher toDomain (RequestPutTeacher body) {
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(UUID.fromString(body.getIdTeacher()));
        teacher.setName(body.getName());

        return teacher;
    }
}
