package com.coursesstore.admin.adapters.http.course.get;

import com.coursesstore.admin.adapters.http.course.get.dto.GetCourseConverter;
import com.coursesstore.admin.adapters.http.course.get.dto.ResponseGetCourse;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.usecases.course.SearchForCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses-store/course")
public class GetCourseController {

    private final SearchForCourse searchForCourse;

    public GetCourseController(SearchForCourse searchForCourse){
        this.searchForCourse = searchForCourse;
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseGetCourse> getCourse() {

        List<Course> listCourses = searchForCourse.execute();

        var responseGetCourse = GetCourseConverter.toResponseGetCourse(listCourses);

        return ResponseEntity.ok(responseGetCourse);
    }



}
