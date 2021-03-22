package com.example.adapters.database.course.desired.model;

import com.example.adapters.database.course.CourseModel;
import com.example.adapters.database.customer.model.CustomerModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "TB_DESIRED_COURSE")
public class DesiredCourseModel {

    @Id
    private String desiredCourseId;
    private Date dateCourseWasDesired;
    private String desireDescription;

    @OneToOne
    private CustomerModel customer;

    @OneToOne
    private CourseModel course;

    public String getDesiredCourseId() {
        return desiredCourseId;
    }

    public void setDesiredCourseId(String desiredCourseId) {
        this.desiredCourseId = desiredCourseId;
    }

    public Date getDateCourseWasDesired() {
        return dateCourseWasDesired;
    }

    public void setDateCourseWasDesired(Date dateCourseWasDesired) {
        this.dateCourseWasDesired = dateCourseWasDesired;
    }

    public String getDesireDescription() {
        return desireDescription;
    }

    public void setDesireDescription(String desireDescription) {
        this.desireDescription = desireDescription;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}