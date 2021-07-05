package com.coursesstore.admin.adapters.http.customer.get.dto;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public final class GetCustomerConverter {

    private GetCustomerConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static ResponseGetCustomer toResponseGetCustomer(List<Customer> listCustomers) {
        var responseGetCustomer = new ResponseGetCustomer();

        if (!listCustomers.isEmpty()) {

            responseGetCustomer.setCustomers(new ArrayList<>());

            for (Customer c : listCustomers) {
                var customer = new ResponseGetCustomer.Customer();
                customer.setIdCustomer(String.valueOf(c.getIdCustomer()));
                customer.setFirstname(c.getFirstname());
                customer.setLastname(c.getLastname());
                customer.setPhone(c.getPhone());
                customer.setEmail(c.getEmail());
                customer.setCompany(c.getCompany());
                customer.setPosition(c.getPosition());
                customer.setLinkedIn(c.getLinkedIn());

                responseGetCustomer.getCustomers().add(customer);
            }
        }

        return responseGetCustomer;
    }

    public static ResponseGetCustomer toResponseGetCustomer(Customer c) {
        var responseGetCustomer = new ResponseGetCustomer();
        responseGetCustomer.setCustomers(new ArrayList<>());
        var customer = new ResponseGetCustomer.Customer();
        customer.setIdCustomer(String.valueOf(c.getIdCustomer()));
        customer.setFirstname(c.getFirstname());
        customer.setLastname(c.getLastname());
        customer.setPhone(c.getPhone());
        customer.setEmail(c.getEmail());
        customer.setCompany(c.getCompany());
        customer.setPosition(c.getPosition());
        customer.setLinkedIn(c.getLinkedIn());

        responseGetCustomer.getCustomers().add(customer);
        return responseGetCustomer;
    }

    public static ResponseGetAcquiredCoursesByCustomer toResponseGetAcquiredCoursesByCustomer
            (List<AcquiredCourse> listAcquiredCoursesByCustomer,List<Course> listNotAcquiredCourses) {
        var responseGetAcquiredCoursesByCustomer = new ResponseGetAcquiredCoursesByCustomer();

        if (!listAcquiredCoursesByCustomer.isEmpty()) {
            responseGetAcquiredCoursesByCustomer.setAcquiredCourses(new ArrayList<>());

            for (AcquiredCourse ac : listAcquiredCoursesByCustomer) {
                var acquiredCourse = new ResponseGetAcquiredCoursesByCustomer.AcquiredCourse();
                acquiredCourse.setCourseName(ac.getCourse().getName());
                acquiredCourse.setIdAcquiredCourse(String.valueOf(ac.getCourse().getIdCourse()));
                acquiredCourse.setAcquisitionDate(String.valueOf(ac.getAcquisitionDate()));
                acquiredCourse.setValuePaid(String.valueOf(ac.getValuePaid()));

                responseGetAcquiredCoursesByCustomer.getAcquiredCourses().add(acquiredCourse);
            }
        }

        if (!listNotAcquiredCourses.isEmpty()) {
            responseGetAcquiredCoursesByCustomer.setNotAcquiredCourses(new ArrayList<>());

            for (Course c : listNotAcquiredCourses) {
                var notAcquiredCourse = new ResponseGetAcquiredCoursesByCustomer.Course();
                notAcquiredCourse.setIdCourse(String.valueOf(c.getIdCourse()));
                notAcquiredCourse.setName(c.getName());

                responseGetAcquiredCoursesByCustomer.getNotAcquiredCourses().add(notAcquiredCourse);
            }
        }

        return responseGetAcquiredCoursesByCustomer;
    }

    public static ResponseGetDesiredCoursesByCustomer toResponseGetDesiredCoursesByCustomer
            (List<DesiredCourse> listDesiredCoursesByCustomer,List<Course> listNotDesiredCourses) {
        var responseGetDesiredCoursesByCustomer = new ResponseGetDesiredCoursesByCustomer();

        if (!listDesiredCoursesByCustomer.isEmpty()) {
            responseGetDesiredCoursesByCustomer.setDesiredCourses(new ArrayList<>());

            for (DesiredCourse dc : listDesiredCoursesByCustomer) {
                var desiredCourse = new ResponseGetDesiredCoursesByCustomer.DesiredCourse();
                desiredCourse.setCourseName(dc.getCourse().getName());
                desiredCourse.setIdDesiredCourse(String.valueOf(dc.getCourse().getIdCourse()));
                desiredCourse.setDesireDate(String.valueOf(dc.getDesireDate()));
                desiredCourse.setDesireDescription(dc.getDesireDescription());

                responseGetDesiredCoursesByCustomer.getDesiredCourses().add(desiredCourse);
            }
        }

        if (!listNotDesiredCourses.isEmpty()) {
            responseGetDesiredCoursesByCustomer.setNotDesiredCourses(new ArrayList<>());

            for (Course c : listNotDesiredCourses) {
                var notDesiredCourse = new ResponseGetDesiredCoursesByCustomer.Course();
                notDesiredCourse.setIdCourse(String.valueOf(c.getIdCourse()));
                notDesiredCourse.setName(c.getName());

                responseGetDesiredCoursesByCustomer.getNotDesiredCourses().add(notDesiredCourse);
            }
        }

        return responseGetDesiredCoursesByCustomer;
    }
}
