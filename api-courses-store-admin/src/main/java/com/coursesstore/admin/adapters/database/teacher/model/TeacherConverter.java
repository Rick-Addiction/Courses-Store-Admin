package com.coursesstore.admin.adapters.database.teacher.model;

import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.util.UUID;

public class TeacherConverter {

    public static TeacherModel toModel(Teacher teacher){
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setIdTeacher(String.valueOf(teacher.getIdTeacher()));
        teacherModel.setName(String.valueOf(teacher.getName()));

        return teacherModel;
    }

    public static Teacher toEntity(TeacherModel teacherModel){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(UUID.fromString(teacherModel.getIdTeacher()));
        teacher.setName(String.valueOf(teacherModel.getName()));

        return teacher;
    }

}
