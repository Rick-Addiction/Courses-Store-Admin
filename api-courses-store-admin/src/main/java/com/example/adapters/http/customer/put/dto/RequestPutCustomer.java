package com.example.adapters.http.customer.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class RequestPutCustomer {

        public RequestPutCustomer(){

        }

        @JsonProperty("id")
        @NotNull
        private UUID id;

        @JsonProperty("firstname")
        @NotBlank
        private String firstName;

        @JsonProperty("lastname")
        @NotBlank
        private String lastName;

        @JsonProperty("doctype")
        @NotBlank
        private String docType;

        @JsonProperty("docnumber")
        @NotNull
        @Size(min = 9, max = 14)
        private String docNumber;


        public UUID getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDocType() {
            return docType;
        }

        public String getDocNumber() {
            return docNumber;
        }
}

