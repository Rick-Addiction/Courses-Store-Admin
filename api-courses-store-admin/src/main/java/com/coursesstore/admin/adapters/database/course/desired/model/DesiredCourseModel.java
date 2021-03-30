package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "TB_DESIRED_COURSE")
public class DesiredCourseModel {

    @Id
    private String idDesiredCourse;
    private LocalDate desireDate;
    private String desireDescription;

    @OneToOne
    private CustomerModel customer;

    @OneToOne
    private CourseModel course;

    public String getIdDesiredCourse() {
        return idDesiredCourse;
    }

    public void setIdDesiredCourse(String idDesiredCourse) {
        this.idDesiredCourse = idDesiredCourse;
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