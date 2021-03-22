package com.example.adapters.database.teacher;

import com.example.adapters.database.teacher.model.TeacherConverter;
import com.example.adapters.database.teacher.model.TeacherModel;
import com.example.core.domain.teacher.Teacher;
import com.example.core.domain.teacher.UpdateTeacherPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateTeacher implements UpdateTeacherPort {

    private final TeacherRepository teacherRepository;

    public UpdateTeacher(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void updateTeacher(Teacher teacher){

        TeacherModel teacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher())).get();

        if(teacherModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        teacherModel = TeacherConverter.toModel(teacher);
        teacherModel.setIdTeacher(String.valueOf(teacher.getIdTeacher()));

        teacherRepository.save(teacherModel);


    }

}
