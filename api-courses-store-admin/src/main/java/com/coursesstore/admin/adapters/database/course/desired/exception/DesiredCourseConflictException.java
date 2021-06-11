package com.coursesstore.admin.adapters.database.course.desired.exception;

public class DesiredCourseConflictException extends RuntimeException {

    public DesiredCourseConflictException() {
        super("Desired Course already registered");
    }

}
