package com.example.adapters.http.course.get;

import com.example.core.domain.course.Course;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.usecases.SearchForAcquiredCoursesByCustomer;
import com.example.core.usecases.SearchForCourse;
import com.example.core.usecases.SearchForDesiredCoursesByCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Course>> getCourse(@RequestParam Map<String,String> courseSearchValues) {

        List<Course> listCourses = searchForCourse.execute(courseSearchValues);
        return ResponseEntity.ok(listCourses);
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
