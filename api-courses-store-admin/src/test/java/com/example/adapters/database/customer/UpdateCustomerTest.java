package com.example.adapters.database.customer;

import com.example.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
public class UpdateCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Update customer when found inside database")
    public void Update_customer_when_found_inside_database(){

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstname("Ellie");
        customer.setLastname("Laster");
        customer.setPhone("+55 11 99999-9999");
        customer.setEmail("email_test@testdomain.com");
        customer.setLinkedIn("linkedin.com/EllieLaster");
        customer.setCompany("Robots with Love");
        customer.setPosition("CEO");

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(customerModel.isPresent());

        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(customer.getIdCustomer());
        customer.setFirstname("Joel");
        customer.setLastname("Laster");
        customer.setPhone("+55 11 99999-9999");
        customer.setEmail("email_test@testdomain.com");
        customer.setLinkedIn("linkedin.com/JoelLaster");
        customer.setCompany("Robots with Love");
        customer.setPosition("CEO");

        UpdateCustomer updateCustomer = new UpdateCustomer(customerRepository);
        updateCustomer.updateCustomer(customerToUpdate);

        Optional<CustomerModel> optionalCustomerModelUpdated = customerRepository.findByIdCustomer(String.valueOf(customerToUpdate.getIdCustomer()));

        assertTrue(optionalCustomerModelUpdated.isPresent());

        CustomerModel customerModelUpdated = optionalCustomerModelUpdated.get();
        Customer customerUpdated = CustomerConverter.toEntity(customerModelUpdated);

        assertEquals(customerUpdated.getIdCustomer(), customerToUpdate.getIdCustomer());
        assertEquals(customerUpdated.getFirstname(),customerToUpdate.getFirstname());
        assertEquals(customerUpdated.getLastname(),customerToUpdate.getLastname());
        assertEquals(customerUpdated.getPhone(),customerToUpdate.getPhone());
        assertEquals(customerUpdated.getEmail(),customerToUpdate.getEmail());
        assertEquals(customerUpdated.getLinkedIn(),customerToUpdate.getLinkedIn());
        assertEquals(customerUpdated.getCompany(),customerToUpdate.getCompany());
        assertEquals(customerUpdated.getPosition(),customerToUpdate.getPosition());

    }
}
