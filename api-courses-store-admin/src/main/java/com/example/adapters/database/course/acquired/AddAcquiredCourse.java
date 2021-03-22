package com.example.adapters.database.course.acquired;


import com.example.adapters.database.course.acquired.exception.AcquiredCourseConflictException;
import com.example.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.example.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.acquired.AddAcquiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class AddAcquiredCourse implements AddAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public AddAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
    }

    @Override
    public void addCourse(AcquiredCourse acquiredCourse) {
        try {
            AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(acquiredCourse);
            acquiredCourseRepository.save(acquiredCourseModel);
        } catch (Exception ex) {
            throw new AcquiredCourseConflictException();
        }
    }
}
