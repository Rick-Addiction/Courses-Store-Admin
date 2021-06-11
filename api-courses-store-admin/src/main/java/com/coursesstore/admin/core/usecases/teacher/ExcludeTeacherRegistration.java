package com.coursesstore.admin.core.usecases.teacher;

import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.teacher.DeleteTeacherPort;
import org.springframework.stereotype.Component;

@Component
public class ExcludeTeacherRegistration {

    private final DeleteTeacherPort deleteTeacherPort;

    public ExcludeTeacherRegistration(DeleteTeacherPort deleteTeacherPort){ this.deleteTeacherPort=deleteTeacherPort; }

    public void execute(String idTeacher) { deleteTeacherPort.deleteTeacher(idTeacher); }

}
