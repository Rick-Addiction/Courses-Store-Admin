package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.teacher.UpdateTeacherPort;
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
