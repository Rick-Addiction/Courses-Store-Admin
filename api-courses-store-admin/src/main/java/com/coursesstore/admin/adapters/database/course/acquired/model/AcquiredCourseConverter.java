package com.coursesstore.admin.adapters.database.course.acquired.model;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.HashSet;

public class AcquiredCourseConverter {

    private AcquiredCourseConverter() {
        throw new IllegalStateException("Utility class");
    }

    public static AcquiredCourseModel toModel(CustomerModel customerModel,AcquiredCourse acquiredCourse ){
        var acquiredCourseModel = new AcquiredCourseModel();
        var acquiredCourseKey = new AcquiredCourseKey(
                    customerModel.getIdCustomer(),
                    String.valueOf(acquiredCourse.getCourse().getIdCourse()));
            acquiredCourseModel.setIdAcquiredCourse(acquiredCourseKey);
            acquiredCourseModel.setAcquisitionDate(acquiredCourse.getAcquisitionDate());
            acquiredCourseModel.setValuePaid(acquiredCourse.getValuePaid());
            acquiredCourseModel.setCourse(CourseConverter.toModel(String.valueOf(acquiredCourse.getCourse().getIdCourse())));
            acquiredCourseModel.setCustomer(customerModel);

        return acquiredCourseModel;
    }

    public static AcquiredCourseModel toModel(AcquiredCourse acquiredCourse){
        var acquiredCourseModel = new AcquiredCourseModel();

            acquiredCourseModel.setAcquisitionDate(acquiredCourse.getAcquisitionDate());
            acquiredCourseModel.setValuePaid(acquiredCourse.getValuePaid());
            acquiredCourseModel.setCourse(CourseConverter.toModel(acquiredCourse.getCourse()));

        return acquiredCourseModel;
    }

    public static Customer toCustomerWithEntity(AcquiredCourseModel acquiredCourseModel){
        var customer = CustomerConverter.toEntity(acquiredCourseModel.getCustomer());

        var acquiredCourse = new AcquiredCourse();
        acquiredCourse.setAcquisitionDate(acquiredCourseModel.getAcquisitionDate());
        acquiredCourse.setValuePaid(acquiredCourseModel.getValuePaid());
        acquiredCourse.setCourse(CourseConverter.toEntity(acquiredCourseModel.getCourse()));

        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        return customer;
    }

    public static AcquiredCourse toEntity(AcquiredCourseModel acquiredCourseModel){
        var acquiredCourse = new AcquiredCourse();
        acquiredCourse.setAcquisitionDate(acquiredCourseModel.getAcquisitionDate());
        acquiredCourse.setValuePaid(acquiredCourseModel.getValuePaid());
        acquiredCourse.setCourse(CourseConverter.toEntity(acquiredCourseModel.getCourse()));

        return acquiredCourse;
    }

}
