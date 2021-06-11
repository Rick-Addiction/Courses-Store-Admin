package com.coursesstore.admin.adapters.http.course.put.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class RequestPutCourse {

        public RequestPutCourse(){

        }

        @JsonProperty("id_course")
        @NotNull
        private String idCourse;

        @JsonProperty("name")
        @NotBlank
        private String name;

        @JsonProperty("original_value")
        @NotBlank
        private String originalValue;

        @JsonProperty("id_teacher_responsible")
        @NotBlank
        private String idTeacherResponsible;

        public String getIdCourse() {
                return idCourse;
        }

        public void setIdCourse(String idCourse) {
                this.idCourse = idCourse;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getOriginalValue() {
                return originalValue;
        }

        public void setOriginalValue(String originalValue) {
                this.originalValue = originalValue;
        }

        public String getIdTeacherResponsible() {
                return idTeacherResponsible;
        }

        public void setIdTeacherResponsible(String idTeacherResponsible) {
                this.idTeacherResponsible = idTeacherResponsible;
        }
}

