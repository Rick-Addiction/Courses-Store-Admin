package com.example.adapters.database.course.acquired;

import com.example.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.example.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.FindAcquiredCoursesByCustomerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FindAcquiredCoursesByCustomer implements FindAcquiredCoursesByCustomerPort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public FindAcquiredCoursesByCustomer(AcquiredCourseRepository acquiredCourseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
    }


    private static final Logger log = LoggerFactory.getLogger(FindAcquiredCoursesByCustomer.class);

    @Override
    public List<AcquiredCourse> findAcquiredCourses(String customerId) {
        Iterable<AcquiredCourseModel> listAcquiredCoursesModel;

        listAcquiredCoursesModel = acquiredCourseRepository.findAll();

        List<AcquiredCourse> listAcquiredCourses = new ArrayList<>();

        for (AcquiredCourseModel c : listAcquiredCoursesModel) {
            listAcquiredCourses.add(AcquiredCourseConverter.toEntity(c));
        }

        return listAcquiredCourses;
    }
}
