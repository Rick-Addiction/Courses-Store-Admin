package com.coursesstore.admin.adapters.database.customer.model;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_CUSTOMER")
public class CustomerModel {

    @Id
    @Column(name = "id_customer")
    private String idCustomer;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String linkedIn;
    private String company;
    private String position;

    @OneToMany(mappedBy = "customer",fetch=FetchType.LAZY)
    private List<AcquiredCourseModel> acquiredCourses = new ArrayList<>();

    @OneToMany(mappedBy = "customer",fetch=FetchType.LAZY)
    private List<DesiredCourseModel> desiredCourses = new ArrayList<>();

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
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
}