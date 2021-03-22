package com.example.adapters.database.course.acquired.model;

import com.example.adapters.database.course.CourseModel;
import com.example.adapters.database.customer.model.CustomerModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TB_ACQUIRED_COURSE")
public class AcquiredCourseModel {

    @Id
    private String acquiredCourseId;
    private Date acquisitionDate;
    private BigDecimal valuePaid;

    @OneToOne
    private CustomerModel customer;

    @OneToOne
    private CourseModel course;

    public String getAcquiredCourseId() {
        return acquiredCourseId;
    }

    public void setAcquiredCourseId(String acquiredCourseId) {
        this.acquiredCourseId = acquiredCourseId;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public BigDecimal getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(BigDecimal valuePaid) {
        this.valuePaid = valuePaid;
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