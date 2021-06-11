package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.UpdateAcquiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UpdateAcquiredCourse implements UpdateAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public UpdateAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository) {
        this.acquiredCourseRepository = acquiredCourseRepository;
    }

    @Override
    public void updateAcquiredCourse(Customer customer){

        AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(customer);

        acquiredCourseRepository.save(acquiredCourseModel);
    }
}
