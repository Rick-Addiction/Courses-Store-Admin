package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.HashSet;

public class DesiredCourseConverter {

    public static DesiredCourseModel toModel(CustomerModel customerModel, DesiredCourse desiredCourse){
        String idCourse = String.valueOf(desiredCourse.getCourse().getIdCourse());

        DesiredCourseModel desiredCourseModel = new DesiredCourseModel();
            DesiredCourseKey desiredCourseKey = new DesiredCourseKey(
                    customerModel.getIdCustomer(),
                    idCourse);
            desiredCourseModel.setIdDesiredCourse(desiredCourseKey);
            desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
            desiredCourseModel.setDesireDate(desiredCourse.getDesireDate());
            desiredCourseModel.setCourse(CourseConverter.toModel(idCourse));
            desiredCourseModel.setCustomer(customerModel);

        return desiredCourseModel;
    }

    public static DesiredCourseModel toModel(DesiredCourse desiredCourse){
            DesiredCourseModel desiredCourseModel = new DesiredCourseModel();
            desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
            desiredCourseModel.setDesireDate(desiredCourse.getDesireDate());
            desiredCourseModel.setCourse(CourseConverter.toModel(desiredCourse.getCourse()));
        return desiredCourseModel;
    }

    public static Customer toCustomerWithEntity(DesiredCourseModel desiredCourseModel){
        Customer customer = CustomerConverter.toEntity(desiredCourseModel.getCustomer());

        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDesireDate());
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

    public static DesiredCourse toEntity(DesiredCourseModel desiredCourseModel){

        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDesireDate());
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        return desiredCourse;
    }

}
