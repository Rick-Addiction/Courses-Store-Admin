package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPostDesiredCourseByCustomer {

    @JsonProperty("id_course")
    @NotBlank
    private String idCourse;

    @JsonProperty("desire_date")
    @NotBlank
    private String desireDate;

    @JsonProperty("desire_description")
    @NotBlank
    private String desireDescription;

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
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
