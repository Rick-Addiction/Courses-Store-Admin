package com.coursesstore.admin.adapters.http.customer.delete;

import com.coursesstore.admin.adapters.http.customer.put.PutCustomerController;
import com.coursesstore.admin.core.domain.customer.Customer;
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

    public DeleteCustomerController(ExcludeCustomerRegistration excludeCustomerRegistration){
        this.excludeCustomerRegistration = excludeCustomerRegistration;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCustomerController.class);

    @DeleteMapping("/delete/{id_customer}")
    public ResponseEntity deleteCustomer (@PathVariable(value = "id_customer", required = false) String idCustomer) {

        Customer customer = new Customer();

        customer.setIdCustomer(UUID.fromString(idCustomer));

        excludeCustomerRegistration.execute(customer);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }

}
