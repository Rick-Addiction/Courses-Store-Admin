package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.HashSet;
import java.util.UUID;

public class DesiredCourseConverter {

    public static DesiredCourseModel toModel(Customer customer){
        DesiredCourseModel desiredCourseModel = new DesiredCourseModel();

        if(!customer.getDesiredCourses().isEmpty()) {
            DesiredCourse desiredCourse = customer.getDesiredCourses().iterator().next();
            desiredCourseModel.setIdDesiredCourse(String.valueOf(desiredCourse.getIdDesiredCourse()));
            desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
            desiredCourseModel.setDesireDate(desiredCourse.getDesireDate());
            desiredCourseModel.setCourse(CourseConverter.toModel(desiredCourse.getCourse()));
            desiredCourseModel.setCustomer(CustomerConverter.toModel(customer));
        }

        return desiredCourseModel;
    }

    public static DesiredCourseModel toModel(DesiredCourse desiredCourse){
            DesiredCourseModel desiredCourseModel = new DesiredCourseModel();
            desiredCourseModel.setIdDesiredCourse(String.valueOf(desiredCourse.getIdDesiredCourse()));
            desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
            desiredCourseModel.setDesireDate(desiredCourse.getDesireDate());
            desiredCourseModel.setCourse(CourseConverter.toModel(desiredCourse.getCourse()));
        return desiredCourseModel;
    }

    public static Customer toEntity(DesiredCourseModel desiredCourseModel){
        Customer customer = CustomerConverter.toEntity(desiredCourseModel.getCustomer());

        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setIdDesiredCourse(UUID.fromString(desiredCourseModel.getIdDesiredCourse()));
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDesireDate());
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

}
