package com.example.adapters.database.course.acquired;

import com.example.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.DeleteAcquiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class DeleteAcquiredCourse implements DeleteAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public DeleteAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository)
    { this.acquiredCourseRepository = acquiredCourseRepository; }

    @Override
    public void deleteAcquiredCourse(AcquiredCourse acquiredCourse) {

        AcquiredCourseModel acquiredCourseToDelete = null;

        if (acquiredCourse.getIdAcquiredCourse() != null)
            acquiredCourseToDelete = acquiredCourseRepository.findByIdAcquiredCourse(acquiredCourse.getIdAcquiredCourse().toString()).get();

        acquiredCourseRepository.delete(acquiredCourseToDelete);
    }
}
