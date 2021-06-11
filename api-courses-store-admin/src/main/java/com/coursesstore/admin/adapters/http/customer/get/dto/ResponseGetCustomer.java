package com.coursesstore.admin.adapters.http.customer.get.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseGetCustomer {


    @JsonProperty("customers")
    @NotNull
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public static class  Customer {
        @JsonProperty("idCustomer")
        @NotNull
        private String idCustomer;

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

        public String getIdCustomer() {
            return idCustomer;
        }

        public void setIdCustomer(String idCustomer) {
            this.idCustomer = idCustomer;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLinkedIn() {
            return linkedIn;
        }

        public void setLinkedIn(String linkedIn) {
            this.linkedIn = linkedIn;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }
}
