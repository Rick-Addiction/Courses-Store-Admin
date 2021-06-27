package com.coursesstore.admin.adapters.database.course.desired.model;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.HashSet;

public class DesiredCourseConverter {

    private DesiredCourseConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static DesiredCourseModel toModel(CustomerModel customerModel, DesiredCourse desiredCourse){
        var idCourse = String.valueOf(desiredCourse.getCourse().getIdCourse());

        var desiredCourseModel = new DesiredCourseModel();
        var desiredCourseKey = new DesiredCourseKey(
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
        var desiredCourseModel = new DesiredCourseModel();
            desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
            desiredCourseModel.setDesireDate(desiredCourse.getDesireDate());
            desiredCourseModel.setCourse(CourseConverter.toModel(desiredCourse.getCourse()));
        return desiredCourseModel;
    }

    public static Customer toCustomerWithEntity(DesiredCourseModel desiredCourseModel){
        var customer = CustomerConverter.toEntity(desiredCourseModel.getCustomer());

        var desiredCourse = new DesiredCourse();
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDesireDate());
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

    public static DesiredCourse toEntity(DesiredCourseModel desiredCourseModel){

        var desiredCourse = new DesiredCourse();
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDesireDate());
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        return desiredCourse;
    }

}
