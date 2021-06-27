package com.coursesstore.admin.adapters.http.course.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class RequestPostCourse {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("original_value")
    @NotBlank
    private String originalValue;

    @JsonProperty("id_teacher_responsible")
    @NotBlank
    private String idTeacherResponsible;

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
