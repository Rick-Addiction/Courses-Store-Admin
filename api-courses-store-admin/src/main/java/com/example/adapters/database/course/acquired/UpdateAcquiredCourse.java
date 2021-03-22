package com.example.adapters.database.course.acquired;


import com.example.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.example.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.UpdateAcquiredCoursePort;
import org.springframework.stereotype.Component;

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

        acquiredCourseModel = AcquiredCourseConverter.toModel(acquiredCourse);
        acquiredCourseModel.setAcquiredCourseId(String.valueOf(acquiredCourse.getIdAcquiredCourse()));

        acquiredCourseRepository.save(acquiredCourseModel);
    }
}
