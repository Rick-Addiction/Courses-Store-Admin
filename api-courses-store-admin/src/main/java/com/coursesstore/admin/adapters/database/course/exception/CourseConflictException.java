package com.coursesstore.admin.adapters.database.course.exception;

public class CourseConflictException extends RuntimeException {

    public CourseConflictException() {
        super("Course already registered");
    }

}
