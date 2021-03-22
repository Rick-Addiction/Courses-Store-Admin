package com.example.adapters.http.course.put;

import com.example.adapters.http.RequestValidator;
import com.example.adapters.http.course.put.dto.PutCourseConverter;
import com.example.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.example.adapters.http.course.put.dto.RequestPutCourse;
import com.example.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.example.core.domain.course.Course;
import com.example.core.domain.course.acquired.AcquiredCourse;
import com.example.core.domain.course.desired.DesiredCourse;
import com.example.core.usecases.UpdateCourseAcquisitionByCustomer;
import com.example.core.usecases.UpdateCourseDesiredByCustomer;
import com.example.core.usecases.UpdateCourseRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/course")
public class PutCourseController {

    private final UpdateCourseRegistration updateCourseRegistration;
    private final UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer;
    private final UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer;
    private final RequestValidator requestValidator;


    public PutCourseController(UpdateCourseRegistration updateCourseRegistration,
                               UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer,
                               UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer,
                               RequestValidator requestValidator) {
        this.updateCourseRegistration = updateCourseRegistration;
        this.requestValidator = requestValidator;
        this.updateCourseAcquisitionByCustomer=updateCourseAcquisitionByCustomer;
        this.updateCourseDesiredByCustomer=updateCourseDesiredByCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCourseController.class);

    @PutMapping("/update")
    public ResponseEntity<Object> updateCourse (@RequestBody RequestPutCourse body) {

        requestValidator.valid(body);

        Course course = PutCourseConverter.toDomain(body);

        updateCourseRegistration.execute(course);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

    @PutMapping("/acquired/update")
    public ResponseEntity<Object> updateAcquiredCourse (@RequestBody RequestPutAcquiredCourse body) {

        requestValidator.valid(body);

        AcquiredCourse course = PutCourseConverter.toDomain(body);

        updateCourseAcquisitionByCustomer.execute(course);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

    @PutMapping("/desired/update")
    public ResponseEntity<Object> updateDesiredCourse (@RequestBody RequestPutDesiredCourse body) {

        requestValidator.valid(body);

        DesiredCourse course = PutCourseConverter.toDomain(body);

        updateCourseDesiredByCustomer.execute(course);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }
}
