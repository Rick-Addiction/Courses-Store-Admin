package com.coursesstore.admin.adapters.http.teacher.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RequestPutTeacher {

        public RequestPutTeacher(){

        }

        @JsonProperty("idTeacher")
        @NotNull
        private String idTeacher;

        @JsonProperty("name")
        @NotBlank
        private String name;

        public String getIdTeacher() {
                return idTeacher;
        }

        public void setIdTeacher(String idTeacher) {
                this.idTeacher = idTeacher;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }
}

