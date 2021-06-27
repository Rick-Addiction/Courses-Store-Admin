package com.coursesstore.admin.adapters.http.teacher.get.dto;
import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class GetTeacherConverter {

    private GetTeacherConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseGetTeacher toResponseGetTeacher(List<Teacher> listTeachers) {
        var responseGetTeacher = new ResponseGetTeacher();

        if(!listTeachers.isEmpty()) {

            responseGetTeacher.setTeachers(new ArrayList<>());

            for (Teacher t : listTeachers) {
                var teacher = new ResponseGetTeacher.Teacher();
                teacher.setIdTeacher(String.valueOf(t.getIdTeacher()));
                teacher.setName(t.getName());

                responseGetTeacher.getTeachers().add(teacher);
            }
        }

        return responseGetTeacher;
    }
}
