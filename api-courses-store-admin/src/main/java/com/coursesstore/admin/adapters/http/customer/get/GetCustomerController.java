package com.coursesstore.admin.adapters.http.customer.get;

import com.coursesstore.admin.adapters.http.customer.get.dto.GetCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.get.dto.ResponseGetCustomer;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.customer.SearchForCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("courses-store/customer")
public class GetCustomerController {

    private final SearchForCustomer searchForCustomer;

    public GetCustomerController(SearchForCustomer searchForCustomer){
        this.searchForCustomer = searchForCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(GetCustomerController.class);

    @GetMapping("/search")
    public ResponseEntity<ResponseGetCustomer> getCustomer() {

        List<Customer> listCustomers = searchForCustomer.execute("");

        ResponseGetCustomer responseGetCustomer = GetCustomerConverter.toResponseGetCustomer(listCustomers);

        return ResponseEntity.ok(responseGetCustomer);
    }

}
