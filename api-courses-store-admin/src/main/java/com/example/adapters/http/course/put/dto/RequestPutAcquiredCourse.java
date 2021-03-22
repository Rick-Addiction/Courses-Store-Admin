package com.example.adapters.http.course.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPutAcquiredCourse {

    public RequestPutAcquiredCourse(){

    }

    @JsonProperty("id_acquired_course")
    @NotBlank
    private String idAcquiredCourse;

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

    public String getIdAcquiredCourse() {
        return idAcquiredCourse;
    }

    public void setIdAcquiredCourse(String idAcquiredCourse) {
        this.idAcquiredCourse = idAcquiredCourse;
    }

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
