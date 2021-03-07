package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class FindCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Find customer by its properties in database")
    public void Find_customer_by_its_properties_in_database(){

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstName("Bruno");
        customer.setLastName("Salgado");
        customer.setDocType("1");
        customer.setDocNumber("111111111");

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        FindCustomer findCustomer = new FindCustomer(customerRepository);

        Map<String, String> firstNameMap = Map.of("firstname", "Bruno");
        Map<String, String> lastNameMap = Map.of("lastname", "Salgado");
        Map<String, String> docTypeAndNumberMap = Map.of("doctype", "1", "docnumber", "111111111");

        List<Customer> customerListFoundByFirstName = findCustomer.findCustomer(firstNameMap);
        List<Customer> customerListFoundByLastName = findCustomer.findCustomer(lastNameMap);
        List<Customer> customerListFoundByDocTypeAndNumber = findCustomer.findCustomer(docTypeAndNumberMap);

        assertTrue(customerListFoundByFirstName.size() > 0);
        assertTrue(customerListFoundByLastName.size() > 0);
        assertTrue(customerListFoundByDocTypeAndNumber.size() > 0);

    }

}
