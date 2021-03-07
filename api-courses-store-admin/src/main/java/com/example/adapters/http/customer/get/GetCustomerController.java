package com.example.adapters.http.customer.get;

import com.example.core.domain.customer.Customer;
import com.example.core.usecases.SearchForCustomer;
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
    public ResponseEntity<List<Customer>> getCustomer(@RequestParam Map<String,String> customerSearchValues) {

        List<Customer> listCustomers = searchForCustomer.execute(customerSearchValues);
        return ResponseEntity.ok(listCustomers);
    }

}
