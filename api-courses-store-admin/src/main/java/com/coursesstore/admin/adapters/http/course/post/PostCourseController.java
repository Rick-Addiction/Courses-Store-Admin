package com.coursesstore.admin.adapters.http.course.post;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.post.dto.PostCourseConverter;
import com.coursesstore.admin.adapters.http.course.post.dto.RequestPostCourse;
import com.coursesstore.admin.core.usecases.course.RegisterNewCourse;
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
    private final RequestValidator<RequestPostCourse> requestValidator;

    public PostCourseController(RegisterNewCourse registerNewCourse,
                                RequestValidator<RequestPostCourse> requestValidator){
        this.registerNewCourse = registerNewCourse;
        this.requestValidator = requestValidator;

    }

    private static final Logger log = LoggerFactory.getLogger(PostCourseController.class);

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> registerCustomer (@RequestBody RequestPostCourse body) {

        requestValidator.valid(body);

        var course = PostCourseConverter.toDomain(body);

        registerNewCourse.execute(course);
        log.info("Course has been registered: {}", course);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
