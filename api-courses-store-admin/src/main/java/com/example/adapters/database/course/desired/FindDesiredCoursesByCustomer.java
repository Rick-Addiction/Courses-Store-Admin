package com.example.adapters.database.course.desired;

import com.example.adapters.database.course.desired.model.DesiredCourseConverter;
import com.example.adapters.database.course.desired.model.DesiredCourseModel;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.domain.course.desired.FindDesiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FindDesiredCoursesByCustomer implements FindDesiredCoursesByCustomerPort {

    private final DesiredCourseRepository desiredCourseRepository;

    public FindDesiredCoursesByCustomer(DesiredCourseRepository desiredCourseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(FindDesiredCoursesByCustomer.class);

    @Override
    public List<DesiredCourse> findDesiredCourse(String customerId) {
        Iterable<DesiredCourseModel> listDesiredCoursesModel;

        listDesiredCoursesModel = desiredCourseRepository.findAll();

        List<DesiredCourse> listDesiredCourses = new ArrayList<>();

        for (DesiredCourseModel c : listDesiredCoursesModel) {
            listDesiredCourses.add(DesiredCourseConverter.toEntity(c));
        }

        return listDesiredCourses;
    }
}
