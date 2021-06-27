package com.coursesstore.admin.core.usecases.course.desired;

import com.coursesstore.admin.core.domain.course.acquired.DeleteAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.acquired.FindAcquiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

@Component
public class AddDesiredCourseToCustomer {

    private final AddDesiredCoursePort addDesiredCourse;
    private final FindAcquiredCoursePort findAcquiredCourse;
    private final DeleteAcquiredCoursePort deleteAcquiredCourse;

    public AddDesiredCourseToCustomer(AddDesiredCoursePort addDesiredCourse,
                                      FindAcquiredCoursePort findAcquiredCourse,
                                      DeleteAcquiredCoursePort deleteAcquiredCourse){
        this.addDesiredCourse=addDesiredCourse;
        this.findAcquiredCourse=findAcquiredCourse;
        this.deleteAcquiredCourse=deleteAcquiredCourse;
    }

    public void execute(String idCustomer, DesiredCourse desiredCourse) {

        var idCourse = String.valueOf(desiredCourse.getCourse().getIdCourse());

        if(findAcquiredCourse.findAcquiredCourse(idCustomer,idCourse) != null){
            deleteAcquiredCourse.deleteAcquiredCourse(idCustomer,idCourse);
        }

        addDesiredCourse.addNewDesiredCourseByCustomer(idCustomer, desiredCourse);
    }

}
