package com.coursesstore.admin.adapters.http.course.delete;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.usecases.course.ExcludeCourseRegistration;
import com.coursesstore.admin.core.usecases.course.acquired.ExcludeCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.ExcludeCourseDesiredByCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @DeleteMapping("/delete")
    public void deleteCourse (@RequestParam(value = "id", required = false) UUID id) {

        Course course = new Course();

        course.setIdCourse(id);

        excludeCourseRegistration.execute(course);
    }

    @DeleteMapping("/acquired/delete")
    public void deleteAcquiredCourse (@RequestParam(value = "id_acquired_course", required = false) String idAcquiredCourse) {

        AcquiredCourse acquiredCourse = new AcquiredCourse();

        acquiredCourse.setIdAcquiredCourse(UUID.fromString(idAcquiredCourse));

        excludeCourseAcquisitionByCustomer.execute(acquiredCourse);
    }

    @DeleteMapping("/desired/delete")
    public void deleteDesiredCourse (@RequestParam(value = "id_desired_course", required = false) String idDesiredCourse) {

        DesiredCourse desiredCourse = new DesiredCourse();

        desiredCourse.setIdDesiredCourse(UUID.fromString(idDesiredCourse));

        excludeCourseDesiredByCustomer.execute(desiredCourse);
    }

}
