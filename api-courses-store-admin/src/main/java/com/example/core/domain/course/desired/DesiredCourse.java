package com.example.core.domain.course.desired;

import com.example.core.domain.course.Course;
import com.example.core.domain.customer.Customer;

import java.util.Date;
import java.util.UUID;

public class DesiredCourse {

    UUID idDesiredCourse;
    Date desireDate;
    String desireDescription;
    Course course;
    Customer customer;

    public UUID getIdDesiredCourse() {
        return idDesiredCourse;
    }

    public void setIdDesiredCourse(UUID idDesiredCourse) {
        this.idDesiredCourse = idDesiredCourse;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDesireDate() {
        return desireDate;
    }

    public void setDesireDate(Date desireDate) {
        this.desireDate = desireDate;
    }

    public String getDesireDescription() {
        return desireDescription;
    }

    public void setDesireDescription(String desireDescription) {
        this.desireDescription = desireDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
