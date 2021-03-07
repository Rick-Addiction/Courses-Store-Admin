package com.example.adapters.http.customer.delete;

import com.example.adapters.http.customer.put.PutCustomerController;
import com.example.core.domain.customer.Customer;
import com.example.core.usecases.ExcludeCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("online-courses/customer")
public class DeleteCustomerController {

    private final ExcludeCustomer excludeCustomer;

    public DeleteCustomerController(ExcludeCustomer excludeCustomer){
        this.excludeCustomer=excludeCustomer;
    }

    private static final Logger log = LoggerFactory.getLogger(PutCustomerController.class);

    @DeleteMapping("/delete")
    public void deleteCustomer (@RequestParam(value = "id", required = false) UUID id) {

        Customer customer = new Customer();

        customer.setIdCustomer(id);

        excludeCustomer.execute(customer);
    }

}
