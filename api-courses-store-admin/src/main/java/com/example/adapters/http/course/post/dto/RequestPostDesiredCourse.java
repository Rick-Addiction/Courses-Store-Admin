package com.example.adapters.http.course.post.dto;

import com.example.core.domain.course.Course;
import com.example.core.domain.customer.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.UUID;

public class RequestPostDesiredCourse {

    public RequestPostDesiredCourse(){

    }

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
