package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB_DESIRED_COURSE")
public class DesiredCourseModel {

    @EmbeddedId
    private AcquiredCourseKey idDesiredCourse;

    private LocalDate desireDate;
    private String desireDescription;

    @ManyToOne(fetch=FetchType.EAGER)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToOne(fetch=FetchType.EAGER)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseModel course;

    public AcquiredCourseKey getIdAcquiredCourse() {
        return idDesiredCourse;
    }

    public void setIdAcquiredCourse(AcquiredCourseKey idDesiredCourse) {
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