package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteAcquiredCourse implements DeleteAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public DeleteAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository)
    { this.acquiredCourseRepository = acquiredCourseRepository; }

    @Override
    public void deleteAcquiredCourse(AcquiredCourse acquiredCourse) {

        Optional<AcquiredCourseModel> acquiredCourseToDelete = null;

        if (acquiredCourse.getIdAcquiredCourse() != null) {
            acquiredCourseToDelete = acquiredCourseRepository.findByIdAcquiredCourse(String.valueOf(acquiredCourse.getIdAcquiredCourse()));
        }

        if(acquiredCourseToDelete.isEmpty()){
            throw new RuntimeException("Cliente não encontrado!");
        }

        acquiredCourseRepository.delete(acquiredCourseToDelete.get());
    }
}
