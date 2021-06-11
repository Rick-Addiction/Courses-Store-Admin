package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
            listDesiredCourses.add(DesiredCourseConverter.toEntity(c).getDesiredCourses().iterator().next());
        }

        return listDesiredCourses;
    }
}
