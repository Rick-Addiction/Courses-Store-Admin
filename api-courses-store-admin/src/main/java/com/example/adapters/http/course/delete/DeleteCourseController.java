package com.example.adapters.http.course.delete;

import com.example.core.domain.course.Course;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.usecases.ExcludeCourseAcquisitionByCustomer;
import com.example.core.usecases.ExcludeCourseDesiredByCustomer;
import com.example.core.usecases.ExcludeCourseRegistration;
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
