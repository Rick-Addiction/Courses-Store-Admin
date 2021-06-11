package com.coursesstore.admin.adapters.http.teacher.post.dto;


import com.coursesstore.admin.core.domain.teacher.Teacher;
import java.util.UUID;

public class PostTeacherConverter {
    public static Teacher toDomain (RequestPostTeacher body){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(UUID.randomUUID());
        teacher.setName(body.getName());

        return teacher;
    }

}
