package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class DeleteDesiredCourse implements DeleteDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public DeleteDesiredCourse(DesiredCourseRepository desiredCourseRepository)
    { this.desiredCourseRepository = desiredCourseRepository; }

    @Override
    public void deleteDesiredCourse(String idDesiredCourse) {

        DesiredCourseModel desiredCourseToDelete = null;

        if (idDesiredCourse != null)
            desiredCourseToDelete = desiredCourseRepository.findByIdDesiredCourse(idDesiredCourse).get();

        desiredCourseRepository.delete(desiredCourseToDelete);
    }
}
