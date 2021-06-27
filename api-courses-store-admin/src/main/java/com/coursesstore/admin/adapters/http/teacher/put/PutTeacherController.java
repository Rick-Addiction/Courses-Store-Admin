package com.coursesstore.admin.adapters.http.teacher.put;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.teacher.put.dto.PutTeacherConverter;
import com.coursesstore.admin.adapters.http.teacher.put.dto.RequestPutTeacher;
import com.coursesstore.admin.core.usecases.teacher.UpdateTeacherRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/teacher")
public class PutTeacherController {

    private final UpdateTeacherRegistration updateTeacherRegistration;
    private final RequestValidator<RequestPutTeacher> requestValidator;


    public PutTeacherController(UpdateTeacherRegistration updateTeacherRegistration,
                                RequestValidator<RequestPutTeacher> requestValidator) {
        this.updateTeacherRegistration = updateTeacherRegistration;
        this.requestValidator = requestValidator;
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateTeacher (@RequestBody RequestPutTeacher body) {
        requestValidator.valid(body);

        var teacher = PutTeacherConverter.toDomain(body);

        updateTeacherRegistration.execute(teacher);

        return ResponseEntity.ok("Register successfully updated!");
    }
}
