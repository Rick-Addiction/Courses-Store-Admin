package com.coursesstore.admin.core.usecases.teacher;

import com.coursesstore.admin.core.domain.customer.CreateCustomerPort;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.CreateTeacherPort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegisterNewTeacher {

    private final CreateTeacherPort createTeacherPort;

    public RegisterNewTeacher(CreateTeacherPort createTeacherPort){
        this.createTeacherPort=createTeacherPort;
    }

    public void execute(Teacher teacher) {
        teacher.setIdTeacher(UUID.randomUUID());
        createTeacherPort.createTeacher(teacher);
    }

}
