package com.coursesstore.admin.core.domain.course.acquired;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class AcquiredCourse {

    Course course;
    LocalDate acquisitionDate;
    BigDecimal valuePaid;


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDate acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public BigDecimal getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(BigDecimal valuePaid) {
        this.valuePaid = valuePaid;
    }
}
