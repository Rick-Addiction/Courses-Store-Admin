package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPostAcquiredCourseByCustomer {

    @JsonProperty("id_course")
    @NotBlank
    private String idCourse;

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
