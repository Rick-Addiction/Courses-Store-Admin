package com.example.adapters.http.course.post;

import com.example.adapters.database.course.acquired.AddAcquiredCourse;
import com.example.adapters.database.course.desired.AddDesiredCourse;
import com.example.adapters.http.RequestValidator;
import com.example.adapters.http.course.post.dto.PostCourseConverter;
import com.example.adapters.http.course.post.dto.RequestPostAcquiredCourse;
import com.example.adapters.http.course.post.dto.RequestPostCourse;
import com.example.adapters.http.course.post.dto.RequestPostDesiredCourse;
import com.example.core.domain.course.Course;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.usecases.AddAcquiredCourseToCustomer;
import com.example.core.usecases.AddDesiredCourseToCustomer;
import com.example.core.usecases.RegisterNewCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("courses-store/course")
public class PostCourseController {

    private final RegisterNewCourse registerNewCourse;
    private final AddAcquiredCourseToCustomer addAcquiredCourseToCustomer;
    private final AddDesiredCourseToCustomer addDesiredCourseToCustomer;
    private final RequestValidator requestValidator;

    public PostCourseController(RegisterNewCourse registerNewCourse,
                                AddAcquiredCourseToCustomer addAcquiredCourseToCustomer,
                                AddDesiredCourseToCustomer addDesiredCourseToCustomer,
                                RequestValidator requestValidator){
        this.registerNewCourse = registerNewCourse;
        this.requestValidator = requestValidator;
        this.addAcquiredCourseToCustomer=addAcquiredCourseToCustomer;
        this.addDesiredCourseToCustomer=addDesiredCourseToCustomer;

    }

    private static final Logger log = LoggerFactory.getLogger(PostCourseController.class);

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> registerCustomer (@RequestBody RequestPostCourse body) {

        requestValidator.valid(body);

        Course course = PostCourseConverter.toDomain(body);

        registerNewCourse.execute(course);
        log.info("Course has been registered: {}", course);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/acquired/add/", consumes = "application/json")
    public ResponseEntity<Object> addAcquiredCourseToCustomer (@RequestBody RequestPostAcquiredCourse body) {

        requestValidator.valid(body);

        AcquiredCourse acquiredCourse = PostCourseConverter.toDomain(body);

        addAcquiredCourseToCustomer.execute(acquiredCourse);
        log.info("Acquired Course has been added to the Customer: {}", acquiredCourse);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/desired/add/", consumes = "application/json")
    public ResponseEntity<Object> addDesiredCourseToCustomer (@RequestBody RequestPostDesiredCourse body) {

        requestValidator.valid(body);

        DesiredCourse desiredCourse = PostCourseConverter.toDomain(body);

        addDesiredCourseToCustomer.execute(desiredCourse);
        log.info("Desired Course has been added to the Customer: {}", desiredCourse);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
