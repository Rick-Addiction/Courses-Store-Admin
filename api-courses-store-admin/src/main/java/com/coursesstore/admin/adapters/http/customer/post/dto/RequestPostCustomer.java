package com.coursesstore.admin.adapters.http.customer.post.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPostCustomer {

    @JsonProperty("first_name")
    @NotBlank
    private String firstname;

    @JsonProperty("last_name")
    @NotBlank
    private String lastname;

    @JsonProperty("phone")
    @NotBlank
    private String phone;

    @JsonProperty("email")
    @NotBlank
    private String email;

    @JsonProperty("linkedin")
    @NotBlank
    private String linkedIn;

    @JsonProperty("company")
    @NotBlank
    private String company;

    @JsonProperty("position")
    @NotBlank
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
