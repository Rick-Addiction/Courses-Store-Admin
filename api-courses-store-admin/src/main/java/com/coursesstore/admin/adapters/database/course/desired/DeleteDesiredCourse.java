package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteDesiredCourse implements DeleteDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public DeleteDesiredCourse(DesiredCourseRepository desiredCourseRepository)
    { this.desiredCourseRepository = desiredCourseRepository; }

    @Override
    public void deleteDesiredCourse(String idCustomer,String idCourse) {


        Optional<DesiredCourseModel> desiredCourseToDelete = desiredCourseRepository.findById(new DesiredCourseKey(idCustomer,idCourse));

        if(desiredCourseToDelete.isEmpty())
            throw new ModelException("Desired Course not found -  Customer " + idCustomer + ", Course "+idCourse+"!");


        desiredCourseRepository.delete(desiredCourseToDelete.get());
    }
}
