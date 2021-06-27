package com.coursesstore.admin.adapters.http.course.get;

import com.coursesstore.admin.adapters.http.course.get.dto.GetCourseConverter;
import com.coursesstore.admin.adapters.http.course.get.dto.ResponseGetCourse;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.usecases.course.SearchForCourse;
import com.coursesstore.admin.core.usecases.course.acquired.SearchForAcquiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.SearchForDesiredCoursesByCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses-store/course")
public class GetCourseController {

    private final SearchForCourse searchForCourse;

    public GetCourseController(SearchForCourse searchForCourse,
                               SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer,
                               SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer){
        this.searchForCourse = searchForCourse;
    }

    private static final Logger log = LoggerFactory.getLogger(GetCourseController.class);

    @GetMapping("/search")
    public ResponseEntity<ResponseGetCourse> getCourse() {

        List<Course> listCourses = searchForCourse.execute();

        ResponseGetCourse responseGetCourse = GetCourseConverter.toResponseGetCourse(listCourses);

        return ResponseEntity.ok(responseGetCourse);
    }



}
