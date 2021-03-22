package com.example.adapters.database.course.desired;



import com.example.adapters.database.course.desired.exception.DesiredCourseConflictException;
import com.example.adapters.database.course.desired.model.DesiredCourseConverter;
import com.example.adapters.database.course.desired.model.DesiredCourseModel;
import com.example.core.domain.course.desired.AddDesiredCoursePort;
import com.example.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class AddDesiredCourse implements AddDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public AddDesiredCourse(DesiredCourseRepository desiredCourseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
    }

    @Override
    public void addCourse(DesiredCourse desiredCourse) {
        try {
            DesiredCourseModel desiredCourseModel = DesiredCourseConverter.toModel(desiredCourse);
            desiredCourseRepository.save(desiredCourseModel);
        } catch (Exception ex) {
            throw new DesiredCourseConflictException();
        }
    }
}
