package com.coursesstore.admin.adapters.database.course.desired;


import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindDesiredCourse implements FindDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public FindDesiredCourse(DesiredCourseRepository desiredCourseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
    }

    @Override
    public DesiredCourse findDesiredCourse(String idCustomer, String idCourse) {
        DesiredCourseKey desiredCourseKey = new DesiredCourseKey(
                String.valueOf(idCustomer),
                String.valueOf(idCourse)
        );

        Optional<DesiredCourseModel> desiredCourseModel = desiredCourseRepository.findById(desiredCourseKey);

        return desiredCourseModel.isPresent() ?
                DesiredCourseConverter.toEntity(desiredCourseModel.get())
                : null;        
    }
}
