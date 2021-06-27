package com.coursesstore.admin.adapters.http.course.get.dto;
import com.coursesstore.admin.core.domain.course.Course;

import java.util.ArrayList;
import java.util.List;

public class GetCourseConverter {

    public static ResponseGetCourse toResponseGetCourse(List<Course> listCourses) {
        ResponseGetCourse responseGetCourse = new ResponseGetCourse();
//TODO Unit Test
        if(!listCourses.isEmpty()) {

            responseGetCourse.setCourses(new ArrayList<>());

            for (Course c : listCourses) {
                ResponseGetCourse.Course course = new ResponseGetCourse.Course();
                course.setIdCourse(String.valueOf(c.getIdCourse()));
                course.setName(c.getName());
                course.setOriginalValue(String.valueOf(c.getOriginalValue()));
                course.setIdTeacherResponsible(String.valueOf(c.getTeacherResponsible().getIdTeacher()));
                course.setTeacherResponsibleName(c.getTeacherResponsible().getName());

                responseGetCourse.getCourses().add(course);
            }
        }

        return responseGetCourse;
    }
}
