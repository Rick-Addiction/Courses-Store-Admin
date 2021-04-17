package com.coursesstore.admin.adapters.http.course.get;

import com.coursesstore.admin.adapters.http.course.get.dto.GetCourseConverter;
import com.coursesstore.admin.adapters.http.course.get.dto.ResponseGetCourse;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.usecases.course.acquired.SearchForAcquiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.course.SearchForCourse;
import com.coursesstore.admin.core.usecases.course.desired.SearchForDesiredCoursesByCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("courses-store/course")
public class GetCourseController {

    private final SearchForCourse searchForCourse;
    private final SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer;
    private final SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer;

    public GetCourseController(SearchForCourse searchForCourse,
                               SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer,
                               SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer){
        this.searchForCourse = searchForCourse;
        this.searchForAcquiredCoursesByCustomer = searchForAcquiredCoursesByCustomer;
        this.searchForDesiredCoursesByCustomer=searchForDesiredCoursesByCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(GetCourseController.class);

    @GetMapping("/search")
    public ResponseEntity<ResponseGetCourse> getCourse() {

        List<Course> listCourses = searchForCourse.execute("");

        ResponseGetCourse responseGetCourse = GetCourseConverter.toResponseGetCourse(listCourses);

        return ResponseEntity.ok(responseGetCourse);
    }

    @GetMapping("/acquired/{id-customer}")
    public ResponseEntity<List<AcquiredCourse>> getAcquiredCourseByCustomer(@PathVariable String idCustomer) {

        List<AcquiredCourse> listAcquiredCourses = searchForAcquiredCoursesByCustomer.execute(idCustomer);
        return ResponseEntity.ok(listAcquiredCourses);
    }

    @GetMapping("/desired/{id-customer}")
    public ResponseEntity<List<DesiredCourse>> getDesiredCourseByCustomer(@PathVariable String idCustomer) {

        List<DesiredCourse> listDesiredCourses = searchForDesiredCoursesByCustomer.execute(idCustomer);
        return ResponseEntity.ok(listDesiredCourses);
    }

}
