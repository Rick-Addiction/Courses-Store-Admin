package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseKey;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteAcquiredCourse implements DeleteAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    public DeleteAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository)
    { this.acquiredCourseRepository = acquiredCourseRepository; }

    @Override
    public void deleteAcquiredCourse(String idCustomer,String idCourse) {

        Optional<AcquiredCourseModel> acquiredCourseToDelete = acquiredCourseRepository.findById(new AcquiredCourseKey(idCustomer, idCourse));


        if(acquiredCourseToDelete.isEmpty()){
            throw new RuntimeException(new String("Acquired Course not found -  Customer " + idCustomer + ", Course "+idCourse+"!"));
        }

        acquiredCourseRepository.delete(acquiredCourseToDelete.get());
    }
}
