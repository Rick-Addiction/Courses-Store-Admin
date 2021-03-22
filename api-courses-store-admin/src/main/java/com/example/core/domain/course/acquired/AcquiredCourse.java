package com.example.core.domain.course.acquired;

import com.example.core.domain.course.Course;
import com.example.core.domain.customer.Customer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class AcquiredCourse {

    UUID idAcquiredCourse;
    Course course;
    Customer customer;
    Date acquisitionDate;
    BigDecimal valuePaid;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UUID getIdAcquiredCourse() {
        return idAcquiredCourse;
    }

    public void setIdAcquiredCourse(UUID idAcquiredCourse) {
        this.idAcquiredCourse = idAcquiredCourse;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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
}
