package com.coursesstore.admin.adapters.database.teacher;


import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.teacher.FindTeacherPort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FindTeacher implements FindTeacherPort {

    private final TeacherRepository teacherRepository;

    public FindTeacher(TeacherRepository teacherRepository){
        this.teacherRepository=teacherRepository;
    }

    @Override
    public List<Teacher> findTeacher(String teacherSearchValues) {
        Iterable<TeacherModel> listTeachersModel;

        listTeachersModel = teacherRepository.findAll();

        List<Teacher> listTeachers = new ArrayList<>();

        for (TeacherModel c : listTeachersModel) {
            var teacher = new Teacher();
            teacher.setIdTeacher(UUID.fromString(c.getIdTeacher()));
            teacher.setName(c.getName());

            listTeachers.add(teacher);
        }

        return listTeachers;
    }
}
