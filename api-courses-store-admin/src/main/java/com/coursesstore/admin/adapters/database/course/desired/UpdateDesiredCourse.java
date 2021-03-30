package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.UpdateDesiredCoursePort;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UpdateDesiredCourse implements UpdateDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public UpdateDesiredCourse(DesiredCourseRepository desiredCourseRepository) {
        this.desiredCourseRepository = desiredCourseRepository;
    }

    @Override
    public void updateDesiredCourse(DesiredCourse desiredCourse){

        DesiredCourseModel desiredCourseModel = desiredCourseRepository.findByIdDesiredCourse(
                String.valueOf(desiredCourse.getIdDesiredCourse())).get();

        if(desiredCourseModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        Customer customer = CustomerConverter.toEntity(desiredCourseModel.getCustomer());
        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        desiredCourseModel = DesiredCourseConverter.toModel(customer);

        desiredCourseRepository.save(desiredCourseModel);
    }
}
