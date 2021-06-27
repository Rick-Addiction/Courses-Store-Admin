package com.coursesstore.admin.adapters.http.teacher.delete;

import com.coursesstore.admin.adapters.http.teacher.put.PutTeacherController;
import com.coursesstore.admin.core.usecases.teacher.ExcludeTeacherRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/teacher")
public class DeleteTeacherController {

    private final ExcludeTeacherRegistration excludeTeacherRegistration;

    public DeleteTeacherController(ExcludeTeacherRegistration excludeTeacherRegistration){
        this.excludeTeacherRegistration = excludeTeacherRegistration;
    }

    private static final Logger log = LoggerFactory.getLogger(PutTeacherController.class);

    @DeleteMapping("/{id_teacher}")
    public ResponseEntity deleteTeacher (@PathVariable(value = "id_teacher", required = false) String idTeacher) {
        excludeTeacherRegistration.execute(idTeacher);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

}
