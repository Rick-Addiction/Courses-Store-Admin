package com.coursesstore.admin.core.usecases.teacher;

import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.domain.teacher.UpdateTeacherPort;
import org.springframework.stereotype.Component;

@Component
public class UpdateTeacherRegistration {

    private final UpdateTeacherPort updateTeacherPort;

    public UpdateTeacherRegistration(UpdateTeacherPort updateTeacherPort) { this.updateTeacherPort = updateTeacherPort; }

    public void execute(Teacher teacher) { updateTeacherPort.updateTeacher(teacher); }

}
