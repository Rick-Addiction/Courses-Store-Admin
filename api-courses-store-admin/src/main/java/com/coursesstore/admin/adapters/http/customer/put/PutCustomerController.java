package com.coursesstore.admin.adapters.http.customer.put;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.put.dto.PutCourseConverter;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.adapters.http.customer.put.dto.PutCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.course.acquired.UpdateCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.UpdateCourseDesiredByCustomer;
import com.coursesstore.admin.core.usecases.customer.UpdateCustomerRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses-store/customer")
public class PutCustomerController {

    private final UpdateCustomerRegistration updateCustomerRegistration;
    private final UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer;
    private final UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer;
    private final RequestValidator requestValidator;


    public PutCustomerController(UpdateCustomerRegistration updateCustomerRegistration,
                                 UpdateCourseAcquisitionByCustomer updateCourseAcquisitionByCustomer,
                                 UpdateCourseDesiredByCustomer updateCourseDesiredByCustomer,
                                 RequestValidator requestValidator) {
        this.updateCustomerRegistration = updateCustomerRegistration;
        this.updateCourseAcquisitionByCustomer=updateCourseAcquisitionByCustomer;
        this.updateCourseDesiredByCustomer=updateCourseDesiredByCustomer;
        this.requestValidator = requestValidator;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCustomerController.class);

    @PutMapping("/update")
    public ResponseEntity<Object> updateCustomer (@RequestBody RequestPutCustomer body) {

        requestValidator.valid(body);

        Customer customer = PutCustomerConverter.toDomain(body);

        updateCustomerRegistration.execute(customer);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

    @PutMapping("/acquired/update")
    public ResponseEntity<Object> updateAcquiredCourse (@RequestBody RequestPutAcquiredCourse body) {

        requestValidator.valid(body);

        Customer customer = PutCustomerConverter.toDomain(body);

        updateCourseAcquisitionByCustomer.execute(customer);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }

    @PutMapping("/desired/update")
    public ResponseEntity<Object> updateDesiredCourse (@RequestBody RequestPutDesiredCourse body) {

        requestValidator.valid(body);

        Customer customer = PutCustomerConverter.toDomain(body);

        updateCourseDesiredByCustomer.execute(customer);

        return ResponseEntity.ok("Registro atualizado com sucesso!");
    }
}
