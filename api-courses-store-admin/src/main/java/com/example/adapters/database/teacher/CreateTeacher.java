package com.example.adapters.database.teacher;

import com.example.adapters.database.teacher.exception.TeacherConflictException;
import com.example.adapters.database.teacher.model.TeacherConverter;
import com.example.adapters.database.teacher.model.TeacherModel;
import com.example.core.domain.teacher.CreateTeacherPort;
import com.example.core.domain.teacher.Teacher;
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
            TeacherModel teacherModel = TeacherConverter.toModel(teacher);
            teacherRepository.save(teacherModel);
        } catch (Exception ex) {
            throw new TeacherConflictException();
        }
    }
}
