package com.coursesstore.admin.adapters.http.customer.delete;

import com.coursesstore.admin.adapters.http.customer.put.PutCustomerController;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.course.acquired.ExcludeCourseAcquisitionByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.ExcludeCourseDesiredByCustomer;
import com.coursesstore.admin.core.usecases.customer.ExcludeCustomerRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("courses-store/customer")
public class DeleteCustomerController {

    private final ExcludeCustomerRegistration excludeCustomerRegistration;
    private final ExcludeCourseAcquisitionByCustomer excludeCourseAcquisitionByCustomer;
    private final ExcludeCourseDesiredByCustomer excludeCourseDesiredByCustomer;

    public DeleteCustomerController(ExcludeCustomerRegistration excludeCustomerRegistration,
                                    ExcludeCourseAcquisitionByCustomer excludeCourseAcquisitionByCustomer,
                                    ExcludeCourseDesiredByCustomer excludeCourseDesiredByCustomer){
        this.excludeCustomerRegistration = excludeCustomerRegistration;
        this.excludeCourseAcquisitionByCustomer = excludeCourseAcquisitionByCustomer;
        this.excludeCourseDesiredByCustomer=excludeCourseDesiredByCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCustomerController.class);

    @DeleteMapping("/{id_customer}")
    public ResponseEntity deleteCustomer (@PathVariable(value = "id_customer", required = false) String idCustomer) {

        Customer customer = new Customer();

        customer.setIdCustomer(UUID.fromString(idCustomer));

        excludeCustomerRegistration.execute(customer);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id_customer}/acquire-course/{id_acquired_course}")
    public ResponseEntity<Object> deleteAcquiredCourseByCustomer (@PathVariable(value = "id_customer") String idCustomer,
                                                               @PathVariable(value = "id_acquired_course") String idAcquiredCourse) {

        excludeCourseAcquisitionByCustomer.execute(idAcquiredCourse);
        log.info("Acquired Course has been added to the Customer: {}", idAcquiredCourse);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id_customer}/desire-course/{id_desired_course}")
    public ResponseEntity<Object> deleteDesiredCourseByCustomer (@PathVariable(value = "id_customer") String idCustomer,
                                                               @PathVariable(value = "id_desired_course") String idDesiredCourse) {

        excludeCourseDesiredByCustomer.execute(idDesiredCourse);
        log.info("Desired Course has been added to the Customer: {}", idDesiredCourse);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

}
