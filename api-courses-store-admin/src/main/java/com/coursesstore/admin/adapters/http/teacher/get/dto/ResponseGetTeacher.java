package com.coursesstore.admin.adapters.http.teacher.get.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseGetTeacher {


    @JsonProperty("teachers")
    @NotNull
    private List<Teacher> teachers;

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers
            (List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public static class  Teacher {
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
}
