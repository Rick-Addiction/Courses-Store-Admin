package com.example.adapters.database.teacher.model;

import com.example.adapters.database.teacher.model.TeacherModel;
import com.example.core.domain.teacher.Teacher;

import java.util.UUID;

public class TeacherConverter {

    public static TeacherModel toModel(Teacher teacher){
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setIdTeacher(String.valueOf(teacher.getIdTeacher()));

        return teacherModel;
    }

    public static Teacher toEntity(TeacherModel teacherModel){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(UUID.fromString(teacherModel.getIdTeacher()));
        teacher.setName(String.valueOf(teacherModel.getIdTeacher()));

        return teacher;
    }

}
