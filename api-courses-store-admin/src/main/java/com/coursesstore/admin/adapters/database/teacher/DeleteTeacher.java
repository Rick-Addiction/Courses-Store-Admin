package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.teacher.DeleteTeacherPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteTeacher implements DeleteTeacherPort {

    private final TeacherRepository teacherRepository;

    public DeleteTeacher(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository; }

    @Override
    public void deleteTeacher(String idTeacher) {

        Optional<TeacherModel> teacherToDelete = teacherRepository.findByIdTeacher(idTeacher);

        if(teacherToDelete.isEmpty())
            throw new ModelException("Teacher " + idTeacher + " not Found");

        teacherRepository.delete(teacherToDelete.get());
    }
}
