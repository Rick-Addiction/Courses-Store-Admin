package com.example.adapters.database.course.acquired.exception;

public class AcquiredCourseConflictException extends RuntimeException {

    public AcquiredCourseConflictException() {
        super("Acquired Course already registered");
    }

}
