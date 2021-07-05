package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
import com.coursesstore.admin.core.domain.teacher.CreateTeacherPort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.stereotype.Component;

@Component
public class CreateTeacher implements CreateTeacherPort {

    private final TeacherRepository teacherRepository;

    public CreateTeacher(TeacherRepository teacherRepository){
        this.teacherRepository=teacherRepository;
    }

    @Override
    public void createTeacher(Teacher teacher) {
        try {
            var teacherModel = TeacherConverter.toModel(teacher);
            teacherRepository.save(teacherModel);
        } catch (Exception ex) {
            throw new ModelException(String.format("Conflict at the creating of a new Teacher: %s",ex.getMessage()));
        }
    }
}
