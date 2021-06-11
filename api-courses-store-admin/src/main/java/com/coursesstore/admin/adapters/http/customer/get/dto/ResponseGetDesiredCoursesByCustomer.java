package com.coursesstore.admin.adapters.http.customer.get.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseGetDesiredCoursesByCustomer {

    @JsonProperty("desired_courses")
    @NotNull
    private List<DesiredCourse> desiredCourses;

    @JsonProperty("not_desired_courses")
    @NotNull
    private List<Course> notDesiredCourses;

    public List<DesiredCourse> getDesiredCourses() {
        return desiredCourses;
    }

    public void setDesiredCourses(List<DesiredCourse> desiredCourses) {
        this.desiredCourses = desiredCourses;
    }

    public List<Course> getNotDesiredCourses() {
        return notDesiredCourses;
    }

    public void setNotDesiredCourses(List<Course> notDesiredCourses) {
        this.notDesiredCourses = notDesiredCourses;
    }

    public static class  DesiredCourse {
        @JsonProperty("id_desired_course")
        @NotNull
        String idDesiredCourse;

        @JsonProperty("course_name")
        @NotNull
        String courseName;

        @JsonProperty("desire_date")
        @NotNull
        String desireDate;

        @JsonProperty("desire_description")
        @NotNull
        String desireDescription;

        public String getIdDesiredCourse() {
            return idDesiredCourse;
        }

        public void setIdDesiredCourse(String idDesiredCourse) {
            this.idDesiredCourse = idDesiredCourse;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getDesireDate() {
            return desireDate;
        }

        public void setDesireDate(String desireDate) {
            this.desireDate = desireDate;
        }

        public String getDesireDescription() {
            return desireDescription;
        }

        public void setDesireDescription(String desireDescription) {
            this.desireDescription = desireDescription;
        }
    }

    public static class Course {
        @JsonProperty("id_course")
        @NotNull
        private String idCourse;

        @JsonProperty("name")
        @NotBlank
        private String name;

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
    }
}
