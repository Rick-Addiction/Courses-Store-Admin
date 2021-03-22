package com.example.adapters.database.teacher.exception;

public class TeacherConflictException extends RuntimeException {

    public TeacherConflictException() {
        super("Customer already registered");
    }

}
