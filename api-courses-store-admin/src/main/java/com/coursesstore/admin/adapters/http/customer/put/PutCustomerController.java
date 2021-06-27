package com.coursesstore.admin.adapters.http.customer.put;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.adapters.http.customer.put.dto.PutCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.usecases.course.acquired.UpdateCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.UpdateCourseDesiredByCustomer;
import com.coursesstore.admin.core.usecases.customer.UpdateCustomerRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("courses-store/customer")
public class PutCustomerController {

    private final UpdateCustomerRegistration updateCustomerRegistration;
    private final UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer;
    private final UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer;
    private final RequestValidator<RequestPutCustomer> requestValidator;

    final ResponseEntity<Object> responseSuccess = ResponseEntity.ok("Register successfully updated!");


    public PutCustomerController(UpdateCustomerRegistration updateCustomerRegistration,
                                 UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer,
                                 UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer,
                                 RequestValidator<RequestPutCustomer> requestValidator) {
        this.updateCustomerRegistration = updateCustomerRegistration;
        this.updateCourseAcquisitionByCustomer=updateCourseAcquisitionByCustomer;
        this.updateCourseDesiredByCustomer=updateCourseDesiredByCustomer;
        this.requestValidator = requestValidator;
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCustomer (@RequestBody RequestPutCustomer body) {

        requestValidator.valid(body);

        var customer = PutCustomerConverter.toDomain(body);

        updateCustomerRegistration.execute(customer);

        return responseSuccess;
    }

    @PutMapping("/acquired/update")
    public ResponseEntity<Object> updateAcquiredCourse (@RequestBody RequestPutAcquiredCourse body) {

        var acquiredCourse = PutCustomerConverter.toDomain(body);

        updateCourseAcquisitionByCustomer.execute(body.getIdCustomer(),acquiredCourse);

        return responseSuccess;
    }

    @PutMapping("/desired/update")
    public ResponseEntity<Object> updateDesiredCourse (@RequestBody RequestPutDesiredCourse body) {

        var desiredCourse = PutCustomerConverter.toDomain(body);

        updateCourseDesiredByCustomer.execute(body.getIdCustomer(), desiredCourse);

        return responseSuccess;
    }
}
