package com.coursesstore.admin.adapters.http.teacher.post;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.teacher.post.dto.PostTeacherConverter;
import com.coursesstore.admin.adapters.http.teacher.post.dto.RequestPostTeacher;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.usecases.teacher.RegisterNewTeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/teacher")
public class PostTeacherController {

    private final RegisterNewTeacher registerNewTeacher;
    private final RequestValidator requestValidator;

    public PostTeacherController(RegisterNewTeacher registerNewTeacher,
                                 RequestValidator requestValidator){
        this.registerNewTeacher = registerNewTeacher;
        this.requestValidator = requestValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PostTeacherController.class);

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> registerTeacher (@RequestBody RequestPostTeacher body) {
        requestValidator.valid(body);

            Teacher teacher = PostTeacherConverter.toDomain(body);

            registerNewTeacher.execute(teacher);
            log.info("Teacher has been registered: {}", teacher);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
