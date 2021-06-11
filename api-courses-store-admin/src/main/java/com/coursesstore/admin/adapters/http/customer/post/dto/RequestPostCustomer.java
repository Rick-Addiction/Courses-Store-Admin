package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class RequestPostCustomer {

    public RequestPostCustomer(){

    }

    @JsonProperty("firstname")
    @NotBlank
    private String firstname;

    @JsonProperty("lastname")
    @NotBlank
    private String lastname;

    @JsonProperty("phone")
    @NotBlank
    private String phone;

    @JsonProperty("email")
    private String email;

    @JsonProperty("linkedin")
    private String linkedIn;

    @JsonProperty("company")
    private String company;

    @JsonProperty("position")
    private String position;


    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
