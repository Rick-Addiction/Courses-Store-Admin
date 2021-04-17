package com.coursesstore.admin.adapters.http.course.get.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseGetCourse {


    @JsonProperty("courses")
    @NotNull
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public static class  Course {
        @JsonProperty("id_course")
        @NotNull
        private String idCourse;

        @JsonProperty("name")
        @NotBlank
        private String name;

        @JsonProperty("original_value")
        @NotBlank
        private String originalValue;

        @JsonProperty("teacher_responsible_name")
        @NotBlank
        private String teacherResponsibleName;

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

        public String getTeacherResponsibleName() {
            return teacherResponsibleName;
        }

        public void setTeacherResponsibleName(String teacherResponsibleName) {
            this.teacherResponsibleName = teacherResponsibleName;
        }
    }
}
