package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindAcquiredCourse implements FindAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public FindAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
    }

    @Override
    public AcquiredCourse findAcquiredCourse(String idCustomer, String idCourse) {
        var acquiredCourseKey = new AcquiredCourseKey(
                idCustomer,
                idCourse
        );

        Optional<AcquiredCourseModel> acquiredCourseModel = acquiredCourseRepository.findById(acquiredCourseKey);

        return acquiredCourseModel.map(AcquiredCourseConverter::toEntity).orElse(null);
    }
}
