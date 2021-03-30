package com.coursesstore.admin.core.domain.teacher;

import java.util.UUID;

public class Teacher {

    UUID idTeacher;

    public UUID getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(UUID idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}
