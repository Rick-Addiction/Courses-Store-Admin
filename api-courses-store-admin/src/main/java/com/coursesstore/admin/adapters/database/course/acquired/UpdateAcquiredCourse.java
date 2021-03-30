package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
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
    public void updateAcquiredCourse(AcquiredCourse acquiredCourse){

        AcquiredCourseModel acquiredCourseModel = acquiredCourseRepository.findByIdAcquiredCourse(String.valueOf(acquiredCourse.getIdAcquiredCourse())).get();

        if(acquiredCourseModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        Customer customer = CustomerConverter.toEntity(acquiredCourseModel.getCustomer());
        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        acquiredCourseModel = AcquiredCourseConverter.toModel(customer);

        acquiredCourseRepository.save(acquiredCourseModel);
    }
}
