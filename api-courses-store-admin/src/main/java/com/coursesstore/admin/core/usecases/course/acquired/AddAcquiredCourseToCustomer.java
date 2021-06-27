package com.coursesstore.admin.core.usecases.course.acquired;

import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DeleteDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.FindDesiredCoursePort;
import org.springframework.stereotype.Component;

@Component
public class AddAcquiredCourseToCustomer {

    private final FindDesiredCoursePort findDesiredCourse;
    private final DeleteDesiredCoursePort deleteDesiredCourse;
    private final AddAcquiredCoursePort addAcquiredCourse;

    public AddAcquiredCourseToCustomer(FindDesiredCoursePort findDesiredCourse,
                                       DeleteDesiredCoursePort deleteDesiredCourse,
                                       AddAcquiredCoursePort addAcquiredCourse){
        this.findDesiredCourse=findDesiredCourse;
        this.deleteDesiredCourse=deleteDesiredCourse;
        this.addAcquiredCourse=addAcquiredCourse;
    }

    public void execute(String idCustomer, AcquiredCourse acquiredCourse) {

        var idCourse = String.valueOf(acquiredCourse.getCourse().getIdCourse());

        if(findDesiredCourse.findDesiredCourse(idCustomer,idCourse) != null){
            deleteDesiredCourse.deleteDesiredCourse(idCustomer,idCourse);
        }

        addAcquiredCourse.addNewAcquiredCourseByCustomer(idCustomer, acquiredCourse);
    }

}
