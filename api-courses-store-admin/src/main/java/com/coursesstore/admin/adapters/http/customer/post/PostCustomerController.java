package com.coursesstore.admin.adapters.http.customer.post;

import com.coursesstore.admin.adapters.http.RequestValidator;
import com.coursesstore.admin.adapters.http.course.post.dto.PostCourseConverter;
import com.coursesstore.admin.adapters.http.course.post.dto.RequestPostAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.post.dto.RequestPostDesiredCourse;
import com.coursesstore.admin.adapters.http.customer.post.dto.PostCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.post.dto.RequestPostCustomer;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.course.acquired.AddAcquiredCourseToCustomer;
import com.coursesstore.admin.core.usecases.course.desired.AddDesiredCourseToCustomer;
import com.coursesstore.admin.core.usecases.customer.RegisterNewCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("courses-store/customer")
public class PostCustomerController {

    private final RegisterNewCustomer registerNewCustomer;
    private final RequestValidator requestValidator;
    private final AddAcquiredCourseToCustomer addAcquiredCourseToCustomer;
    private final AddDesiredCourseToCustomer addDesiredCourseToCustomer;

    public PostCustomerController(RegisterNewCustomer registerNewCustomer,
                                  AddAcquiredCourseToCustomer addAcquiredCourseToCustomer,
                                  AddDesiredCourseToCustomer addDesiredCourseToCustomer,
                                  RequestValidator requestValidator){
        this.registerNewCustomer = registerNewCustomer;
        this.requestValidator = requestValidator;
        this.addAcquiredCourseToCustomer=addAcquiredCourseToCustomer;
        this.addDesiredCourseToCustomer=addDesiredCourseToCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(PostCustomerController.class);

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> registerCustomer (@RequestBody RequestPostCustomer body) {

        requestValidator.valid(body);

            Customer customer = PostCustomerConverter.toDomain(body);

            registerNewCustomer.execute(customer);
            log.info("Customer has been registered: {}", customer);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id_customer}/acquire-course", consumes = "application/json")
    public ResponseEntity<Object> addAcquiredCourseToCustomer (@PathVariable(value = "id_customer") String idCustomer, @RequestBody RequestPostAcquiredCourse body) {

        requestValidator.valid(body);

        Customer customer = PostCustomerConverter.toDomain(idCustomer, body);

        addAcquiredCourseToCustomer.execute(customer);
        log.info("Acquired Course has been added to the Customer: {}", customer);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id_customer}/desire-course", consumes = "application/json")
    public ResponseEntity<Object> addAcquiredCourseToCustomer (@PathVariable(value = "id_customer") String idCustomer, @RequestBody RequestPostDesiredCourse body) {

        requestValidator.valid(body);

        Customer customer = PostCustomerConverter.toDomain(idCustomer, body);

        addDesiredCourseToCustomer.execute(customer);
        log.info("Desired Course has been added to the Customer: {}", customer);

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
