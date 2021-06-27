package com.coursesstore.admin.adapters.http.teacher.put;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.teacher.put.dto.PutTeacherConverter;
import com.coursesstore.admin.adapters.http.teacher.put.dto.RequestPutTeacher;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.coursesstore.admin.core.usecases.teacher.UpdateTeacherRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/teacher")
public class PutTeacherController {

    private final UpdateTeacherRegistration updateTeacherRegistration;
    private final RequestValidator requestValidator;


    public PutTeacherController(UpdateTeacherRegistration updateTeacherRegistration,
                                RequestValidator requestValidator) {
        this.updateTeacherRegistration = updateTeacherRegistration;
        this.requestValidator = requestValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PutTeacherController.class);

    @PutMapping("/update")
    public ResponseEntity<Object> updateTeacher (@RequestBody RequestPutTeacher body) {
        requestValidator.valid(body);

        Teacher teacher = PutTeacherConverter.toDomain(body);

        updateTeacherRegistration.execute(teacher);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }
}
