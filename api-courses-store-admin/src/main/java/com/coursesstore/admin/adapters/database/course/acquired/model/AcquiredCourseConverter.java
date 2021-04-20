package com.coursesstore.admin.adapters.database.course.acquired.model;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;

import java.util.HashSet;
import java.util.UUID;

public class AcquiredCourseConverter {

    public static AcquiredCourseModel toModel(Customer customer){
        AcquiredCourseModel acquiredCourseModel = new AcquiredCourseModel();

        if(!customer.getAcquiredCourses().isEmpty()) {
            AcquiredCourse acquiredCourse = customer.getAcquiredCourses().iterator().next();
            AcquiredCourseKey acquiredCourseKey = new AcquiredCourseKey(
                    String.valueOf(customer.getIdCustomer()),
                    String.valueOf(acquiredCourse.getCourse().getIdCourse()));
            acquiredCourseModel.setIdAcquiredCourse(acquiredCourseKey);
            acquiredCourseModel.setAcquisitionDate(acquiredCourse.getAcquisitionDate());
            acquiredCourseModel.setValuePaid(acquiredCourse.getValuePaid());
            acquiredCourseModel.setCourse(CourseConverter.toModel(acquiredCourse.getCourse()));
            acquiredCourseModel.setCustomer(CustomerConverter.toModel(customer));
        }

        return acquiredCourseModel;
    }

    public static AcquiredCourseModel toModel(AcquiredCourse acquiredCourse){
        AcquiredCourseModel acquiredCourseModel = new AcquiredCourseModel();

            acquiredCourseModel.setAcquisitionDate(acquiredCourse.getAcquisitionDate());
            acquiredCourseModel.setValuePaid(acquiredCourse.getValuePaid());
            acquiredCourseModel.setCourse(CourseConverter.toModel(acquiredCourse.getCourse()));

        return acquiredCourseModel;
    }

    public static Customer toEntity(AcquiredCourseModel acquiredCourseModel){
        Customer customer = CustomerConverter.toEntity(acquiredCourseModel.getCustomer());

        AcquiredCourse acquiredCourse = new AcquiredCourse();
        acquiredCourse.setAcquisitionDate(acquiredCourseModel.getAcquisitionDate());
        acquiredCourse.setValuePaid(acquiredCourseModel.getValuePaid());
        acquiredCourse.setCourse(CourseConverter.toEntity(acquiredCourseModel.getCourse()));

        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        return customer;
    }

}
