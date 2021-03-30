package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.teacher.DeleteTeacherPort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.stereotype.Component;

@Component
public class DeleteTeacher implements DeleteTeacherPort {

    private final TeacherRepository teacherRepository;

    public DeleteTeacher(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository; }

    @Override
    public void deleteTeacher(Teacher teacher) {

        TeacherModel teacherToDelete = null;

        if (teacher.getIdTeacher() != null)
            teacherToDelete = teacherRepository.findByIdTeacher(
                    teacher.getIdTeacher().toString()).get();

        teacherRepository.delete(teacherToDelete);
    }
}
