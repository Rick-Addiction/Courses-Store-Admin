package com.coursesstore.admin.adapters.http.customer.get.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseGetAcquiredCoursesByCustomer {

    @JsonProperty("acquired_courses")
    @NotNull
    private List<AcquiredCourse> acquiredCourses;

    @JsonProperty("not_acquired_courses")
    @NotNull
    private List<Course> notAcquiredCourses;

    public List<AcquiredCourse> getAcquiredCourses() {
        return acquiredCourses;
    }

    public void setAcquiredCourses(List<AcquiredCourse> acquiredCourses) {
        this.acquiredCourses = acquiredCourses;
    }

    public List<Course> getNotAcquiredCourses() {
        return notAcquiredCourses;
    }

    public void setNotAcquiredCourses(List<Course> notAcquiredCourses) {
        this.notAcquiredCourses = notAcquiredCourses;
    }

    public static class  AcquiredCourse {

        @JsonProperty("id_acquired_course")
        @NotNull
        String idAcquiredCourse;

        @JsonProperty("course_name")
        @NotNull
        String courseName;

        @JsonProperty("acquisition_date")
        @NotNull
        String acquisitionDate;

        @JsonProperty("value_paid")
        @NotNull
        String valuePaid;

        public String getIdAcquiredCourse() {
            return idAcquiredCourse;
        }

        public void setIdAcquiredCourse(String idAcquiredCourse) {
            this.idAcquiredCourse = idAcquiredCourse;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getAcquisitionDate() {
            return acquisitionDate;
        }

        public void setAcquisitionDate(String acquisitionDate) {
            this.acquisitionDate = acquisitionDate;
        }

        public String getValuePaid() {
            return valuePaid;
        }

        public void setValuePaid(String valuePaid) {
            this.valuePaid = valuePaid;
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
