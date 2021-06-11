package com.coursesstore.admin.adapters.http.course.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPutDesiredCourse {

    public RequestPutDesiredCourse(){

    }

    @JsonProperty("id_desired_course")
    @NotBlank
    private String idDesiredCourse;

    @JsonProperty("id_course")
    @NotBlank
    private String idCourse;

    @JsonProperty("id_customer")
    @NotBlank
    private String idCustomer;

    @JsonProperty("desire_date")
    @NotBlank
    private String desireDate;

    @JsonProperty("desire_description")
    @NotBlank
    private String desireDescription;

    public String getIdDesiredCourse() {
        return idDesiredCourse;
    }

    public void setIdDesiredCourse(String idDesiredCourse) {
        this.idDesiredCourse = idDesiredCourse;
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

    public String getDesireDate() {
        return desireDate;
    }

    public void setDesireDate(String desireDate) {
        this.desireDate = desireDate;
    }

    public String getDesireDescription() {
        return desireDescription;
    }

    public void setDesireDescription(String desireDescription) {
        this.desireDescription = desireDescription;
    }
}
