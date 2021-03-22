package com.example.adapters.database.course.desired;

import com.example.adapters.database.course.desired.model.DesiredCourseConverter;
import com.example.adapters.database.course.desired.model.DesiredCourseModel;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.domain.course.desired.UpdateDesiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class UpdateDesiredCourse implements UpdateDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public UpdateDesiredCourse(DesiredCourseRepository desiredCourseRepository) {
        this.desiredCourseRepository = desiredCourseRepository;
    }

    @Override
    public void updateDesiredCourse(DesiredCourse desiredCourse){

        DesiredCourseModel desiredCourseModel = desiredCourseRepository.findByIdDesiredCourse(String.valueOf(desiredCourse.getIdDesiredCourse())).get();

        if(desiredCourseModel == null)
            throw new RuntimeException("Cliente não encontrado!");

        desiredCourseModel = DesiredCourseConverter.toModel(desiredCourse);
        desiredCourseModel.setDesiredCourseId(String.valueOf(desiredCourse.getIdDesiredCourse()));

        desiredCourseRepository.save(desiredCourseModel);
    }
}
