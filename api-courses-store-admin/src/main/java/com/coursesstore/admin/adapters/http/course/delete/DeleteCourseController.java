package com.coursesstore.admin.adapters.http.course.delete;

import com.coursesstore.admin.core.usecases.course.ExcludeCourseRegistration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/course")
public class DeleteCourseController {

    private final ExcludeCourseRegistration excludeCourseRegistration;

    public DeleteCourseController(ExcludeCourseRegistration excludeCourseRegistration){
        this.excludeCourseRegistration = excludeCourseRegistration;
    }

    @DeleteMapping("/{id_course}")
    public void deleteCourse (@PathVariable(value = "id_course", required = false) String idCourse) {
        excludeCourseRegistration.execute(idCourse);
    }

}
