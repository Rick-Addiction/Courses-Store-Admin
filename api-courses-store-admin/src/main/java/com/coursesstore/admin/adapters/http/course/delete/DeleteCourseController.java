package com.coursesstore.admin.adapters.http.course.delete;

import com.coursesstore.admin.core.usecases.course.ExcludeCourseRegistration;
import com.coursesstore.admin.core.usecases.course.acquired.ExcludeCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.ExcludeCourseDesiredByCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("online-courses/course")
public class DeleteCourseController {

    private final ExcludeCourseRegistration excludeCourseRegistration;
    private final ExcludeCourseAcquisitionByCustomer excludeCourseAcquisitionByCustomer;
    private final ExcludeCourseDesiredByCustomer excludeCourseDesiredByCustomer;

    public DeleteCourseController(ExcludeCourseRegistration excludeCourseRegistration,
                                  ExcludeCourseAcquisitionByCustomer excludeCourseAcquisitionByCustomer,
                                  ExcludeCourseDesiredByCustomer excludeCourseDesiredByCustomer){
        this.excludeCourseRegistration = excludeCourseRegistration;
        this.excludeCourseAcquisitionByCustomer=excludeCourseAcquisitionByCustomer;
        this.excludeCourseDesiredByCustomer=excludeCourseDesiredByCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(DeleteCourseController.class);

    @DeleteMapping("/{id_course}")
    public void deleteCourse (@PathVariable(value = "id_course", required = false) String idCourse) {

        excludeCourseRegistration.execute(idCourse);
    }

}
