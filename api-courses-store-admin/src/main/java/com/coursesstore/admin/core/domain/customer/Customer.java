package com.coursesstore.admin.core.domain.customer;

import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;

import java.util.Set;
import java.util.UUID;

public class Customer {

    public Customer(){

    }

    private UUID idCustomer;
    private String firstname;
    private String lastname;
        private String phone;
    private String email;
    private String linkedIn;
    private String company;
    private String position;
    private Set<AcquiredCourse> acquiredCourses;
    private Set<DesiredCourse> desiredCourses;

    public UUID getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(UUID idCustomer) {
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

    public Set<AcquiredCourse> getAcquiredCourses() {
        return acquiredCourses;
    }

    public void setAcquiredCourses(Set<AcquiredCourse> acquiredCourses) {
        this.acquiredCourses = acquiredCourses;
    }

    public Set<DesiredCourse> getDesiredCourses() {
        return desiredCourses;
    }

    public void setDesiredCourses(Set<DesiredCourse> desiredCourses) {
        this.desiredCourses = desiredCourses;
    }
}
