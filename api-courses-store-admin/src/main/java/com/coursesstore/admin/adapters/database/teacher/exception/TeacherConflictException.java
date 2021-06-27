package com.coursesstore.admin.adapters.database.teacher.exception;

public class TeacherConflictException extends RuntimeException {

    public TeacherConflictException() {
        super("Customer already registered");
    }

}
