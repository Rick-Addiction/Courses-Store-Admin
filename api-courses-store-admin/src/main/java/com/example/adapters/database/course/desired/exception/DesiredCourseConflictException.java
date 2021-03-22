package com.example.adapters.database.course.desired.exception;

public class DesiredCourseConflictException extends RuntimeException {

    public DesiredCourseConflictException() {
        super("Desired Course already registered");
    }

}
