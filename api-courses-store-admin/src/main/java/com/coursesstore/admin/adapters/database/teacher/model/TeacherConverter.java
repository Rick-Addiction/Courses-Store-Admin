package com.coursesstore.admin.adapters.database.teacher.model;

import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.util.UUID;

public class TeacherConverter {

    private TeacherConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static TeacherModel toModel(Teacher teacher){
        var teacherModel = new TeacherModel();
        teacherModel.setIdTeacher(String.valueOf(teacher.getIdTeacher()));
        teacherModel.setName(String.valueOf(teacher.getName()));

        return teacherModel;
    }

    public static Teacher toEntity(TeacherModel teacherModel){
        var teacher = new Teacher();
        teacher.setIdTeacher(UUID.fromString(teacherModel.getIdTeacher()));
        teacher.setName(String.valueOf(teacherModel.getName()));

        return teacher;
    }

}
