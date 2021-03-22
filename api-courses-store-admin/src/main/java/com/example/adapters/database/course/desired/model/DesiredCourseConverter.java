package com.example.adapters.database.course.desired.model;

import com.example.adapters.database.course.CourseConverter;

import com.example.adapters.database.customer.model.CustomerConverter;
import com.example.core.domain.course.desired.DesiredCourse;

import java.util.UUID;

public class DesiredCourseConverter {

    public static DesiredCourseModel toModel(DesiredCourse desiredCourse){
        DesiredCourseModel desiredCourseModel = new DesiredCourseModel();
        desiredCourseModel.setDesiredCourseId(String.valueOf(desiredCourse.getIdDesiredCourse()));
        desiredCourseModel.setDesireDescription(desiredCourse.getDesireDescription());
        desiredCourseModel.setDateCourseWasDesired(desiredCourse.getDesireDate());
        desiredCourseModel.setCustomer(CustomerConverter.toModel(desiredCourse.getCustomer()));
        desiredCourseModel.setCourse(CourseConverter.toModel(desiredCourse.getCourse()));

        return desiredCourseModel;
    }

    public static DesiredCourse toEntity(DesiredCourseModel desiredCourseModel){

        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setIdDesiredCourse(UUID.fromString(desiredCourseModel.getDesiredCourseId()));
        desiredCourse.setDesireDescription(desiredCourseModel.getDesireDescription());
        desiredCourse.setDesireDate(desiredCourseModel.getDateCourseWasDesired());
        desiredCourse.setCustomer(CustomerConverter.toEntity(desiredCourseModel.getCustomer()));
        desiredCourse.setCourse(CourseConverter.toEntity(desiredCourseModel.getCourse()));

        return desiredCourse;
    }

}
