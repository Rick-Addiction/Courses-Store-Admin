package com.coursesstore.admin.adapters.http.course.put;

import com.coursesstore.admin.adapters.http.course.put.dto.PutCourseConverter;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutCourse;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.course.UpdateCourseRegistration;
import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.core.usecases.course.acquired.UpdateCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.UpdateCourseDesiredByCustomer;
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
    private final RequestValidator requestValidator;


    public PutCourseController(UpdateCourseRegistration updateCourseRegistration,
                               UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer,
                               UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer,
                               RequestValidator requestValidator) {
        this.updateCourseRegistration = updateCourseRegistration;
        this.requestValidator = requestValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCourseController.class);

    @PutMapping("/update")
    public ResponseEntity<Object> updateCourse (@RequestBody RequestPutCourse body) {

        requestValidator.valid(body);

        Course course = PutCourseConverter.toDomain(body);

        updateCourseRegistration.execute(course);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

}
