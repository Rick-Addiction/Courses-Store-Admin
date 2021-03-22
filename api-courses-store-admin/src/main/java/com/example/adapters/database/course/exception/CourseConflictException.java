package com.example.adapters.database.course.exception;

public class CourseConflictException extends RuntimeException {

    public CourseConflictException() {
        super("Course already registered");
    }

}
