package com.coursesstore.admin.adapters.http.course.put;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.put.dto.PutCourseConverter;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutCourse;
import com.coursesstore.admin.core.usecases.course.UpdateCourseRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/course")
public class PutCourseController {

    private final UpdateCourseRegistration updateCourseRegistration;
    private final RequestValidator<RequestPutCourse> requestValidator;


    public PutCourseController(UpdateCourseRegistration updateCourseRegistration,
                               RequestValidator<RequestPutCourse> requestValidator) {
        this.updateCourseRegistration = updateCourseRegistration;
        this.requestValidator = requestValidator;
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCourse (@RequestBody RequestPutCourse body) {

        requestValidator.valid(body);

        var course = PutCourseConverter.toDomain(body);

        updateCourseRegistration.execute(course);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

}
