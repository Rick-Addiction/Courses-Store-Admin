package com.example.adapters.http.customer.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class RequestPutCustomer {

        public RequestPutCustomer(){

        }

        @JsonProperty("idCustomer")
        @NotNull
        private UUID idCustomer;

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

        @JsonProperty("linkedIn")
        private String linkedIn;

        @JsonProperty("company")
        private String company;

        @JsonProperty("position")
        private String position;

        public UUID getIdCustomer() {
                return idCustomer;
        }

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
}

