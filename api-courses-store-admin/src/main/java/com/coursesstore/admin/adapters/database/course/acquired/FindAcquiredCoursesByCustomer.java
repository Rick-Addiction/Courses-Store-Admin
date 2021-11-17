package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAcquiredCoursesByCustomer implements FindAcquiredCoursesByCustomerPort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public FindAcquiredCoursesByCustomer(AcquiredCourseRepository acquiredCourseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
    }

    @Override
    public List<AcquiredCourse> findAcquiredCourses(String customerId) {
        Iterable<AcquiredCourseModel> listAcquiredCoursesModel = acquiredCourseRepository.findByCustomerId(customerId);

        List<AcquiredCourse> listAcquiredCourses = new ArrayList<>();

        for (AcquiredCourseModel c : listAcquiredCoursesModel) {
            listAcquiredCourses.add(AcquiredCourseConverter.toCustomerWithEntity(c).getAcquiredCourses().iterator().next());
        }

        return listAcquiredCourses;
    }
}
