package com.example.adapters.http.course.post.dto;

import com.example.core.domain.course.Course;
import com.example.core.domain.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class RequestPostAcquiredCourse {

    public RequestPostAcquiredCourse(){

    }

    @JsonProperty("id_course")
    @NotBlank
    private String idCourse;

    @JsonProperty("id_customer")
    @NotBlank
    private String idCustomer;

    @JsonProperty("acquisition_date")
    @NotBlank
    private String acquisitionDate;

    @JsonProperty("value_paid")
    @NotBlank
    private String valuePaid;

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getValuePaid() {
        return valuePaid;
    }

    public void setValuePaid(String valuePaid) {
        this.valuePaid = valuePaid;
    }
}
