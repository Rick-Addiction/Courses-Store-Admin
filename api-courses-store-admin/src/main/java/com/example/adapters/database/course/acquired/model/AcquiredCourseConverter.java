package com.example.adapters.database.course.acquired.model;

import com.example.adapters.database.course.CourseConverter;
import com.example.adapters.database.customer.model.CustomerConverter;

import com.example.core.domain.course.acquired.AcquiredCourse;

import java.util.UUID;

public class AcquiredCourseConverter {

    public static AcquiredCourseModel toModel(AcquiredCourse acquiredCourse){
        AcquiredCourseModel acquiredCourseModel = new AcquiredCourseModel();
        acquiredCourseModel.setAcquiredCourseId(String.valueOf(acquiredCourse.getIdAcquiredCourse()));
        acquiredCourseModel.setAcquisitionDate(acquiredCourse.getAcquisitionDate());
        acquiredCourseModel.setValuePaid(acquiredCourse.getValuePaid());
        acquiredCourseModel.setCustomer(CustomerConverter.toModel(acquiredCourse.getCustomer()));
        acquiredCourseModel.setCourse(CourseConverter.toModel(acquiredCourse.getCourse()));

        return acquiredCourseModel;
    }

    public static AcquiredCourse toEntity(AcquiredCourseModel acquiredCourseModel){

        AcquiredCourse acquiredCourse = new AcquiredCourse();
        acquiredCourse.setIdAcquiredCourse(UUID.fromString(acquiredCourseModel.getAcquiredCourseId()));
        acquiredCourse.setAcquisitionDate(acquiredCourseModel.getAcquisitionDate());
        acquiredCourse.setValuePaid(acquiredCourseModel.getValuePaid());
        acquiredCourse.setCustomer(CustomerConverter.toEntity(acquiredCourseModel.getCustomer()));
        acquiredCourse.setCourse(CourseConverter.toEntity(acquiredCourseModel.getCourse()));

        return acquiredCourse;
    }

}
