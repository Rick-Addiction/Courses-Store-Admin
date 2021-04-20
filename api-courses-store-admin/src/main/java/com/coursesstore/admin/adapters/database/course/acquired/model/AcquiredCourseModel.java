package com.coursesstore.admin.adapters.database.course.acquired.model;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_ACQUIRED_COURSE")
@DynamicUpdate
public class AcquiredCourseModel {

    @EmbeddedId
    private AcquiredCourseKey idAcquiredCourse;

    @ManyToOne(fetch=FetchType.EAGER)
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToOne(fetch=FetchType.EAGER)
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private CourseModel course;

    private LocalDate acquisitionDate;
    private BigDecimal valuePaid;

    public AcquiredCourseKey getIdAcquiredCourse() {
        return idAcquiredCourse;
    }

    public void setIdAcquiredCourse(AcquiredCourseKey idAcquiredCourse) {
        this.idAcquiredCourse = idAcquiredCourse;
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