package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.teacher.UpdateTeacherPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateTeacher implements UpdateTeacherPort {

    private final TeacherRepository teacherRepository;

    public UpdateTeacher(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void updateTeacher(Teacher teacher){
        var idTeacher = String.valueOf(teacher.getIdTeacher());

        Optional<TeacherModel> teacherModelOptional = teacherRepository.findByIdTeacher(idTeacher);

        if(teacherModelOptional.isEmpty())
            throw new ModelException("Teacher not found -  Teacher " + idTeacher +"!");

        var teacherModel = TeacherConverter.toModel(teacher);
        teacherModel.setIdTeacher(String.valueOf(teacher.getIdTeacher()));

        teacherRepository.save(teacherModel);


    }

}
