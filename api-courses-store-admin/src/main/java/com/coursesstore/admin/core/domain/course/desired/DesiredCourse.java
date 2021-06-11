package com.coursesstore.admin.core.domain.course.desired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class DesiredCourse {

    LocalDate desireDate;
    String desireDescription;
    Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getDesireDate() {
        return desireDate;
    }

    public void setDesireDate(LocalDate desireDate) {
        this.desireDate = desireDate;
    }

    public String getDesireDescription() {
        return desireDescription;
    }

    public void setDesireDescription(String desireDescription) {
        this.desireDescription = desireDescription;
    }

}
