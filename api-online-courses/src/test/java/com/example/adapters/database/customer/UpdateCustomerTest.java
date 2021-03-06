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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class UpdateCustomerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Update customer when found inside database")
    public void Update_customer_when_found_inside_database(){

        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstName("Bruno");
        customer.setLastName("Salgado");
        customer.setDocType("1");
        customer.setDocNumber("111111111");

        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(String.valueOf(customer.getIdCustomer()));

        assertTrue(customerModel.isPresent());

        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(customer.getIdCustomer());
        customerToUpdate.setFirstName("Cesar");
        customerToUpdate.setLastName("Rocha");
        customerToUpdate.setDocType("2");
        customerToUpdate.setDocNumber("222222222");

        UpdateCustomer updateCustomer = new UpdateCustomer(customerRepository);
        updateCustomer.updateCustomer(customerToUpdate);

        Optional<CustomerModel> optionalCustomerModelUpdated = customerRepository.findByIdCustomer(String.valueOf(customerToUpdate.getIdCustomer()));

        assertTrue(optionalCustomerModelUpdated.isPresent());

        CustomerModel customerModelUpdated = optionalCustomerModelUpdated.get();
        Customer customerUpdated = CustomerConverter.toEntity(customerModelUpdated);

        assertNotEquals(customerUpdated.getFirstName(), customer.getFirstName());
        assertNotEquals(customerUpdated.getLastName(), customer.getLastName());
        assertNotEquals(customerUpdated.getDocType(), customer.getDocType());
        assertNotEquals(customerUpdated.getDocNumber(), customer.getDocNumber());

        assertEquals(customerUpdated.getFirstName(), customerToUpdate.getFirstName());
        assertEquals(customerUpdated.getLastName(), customerToUpdate.getLastName());
        assertEquals(customerUpdated.getDocType(), customerToUpdate.getDocType());
        assertEquals(customerUpdated.getDocNumber(), customerToUpdate.getDocNumber());

    }

/*    @Test
    @DisplayName("Not update when there is customer for the id")
    public void Not_update_when_there_is_customer_for_the_id(){

        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(UUID.randomUUID());
        customerToUpdate.setFirstName("Cesar");
        customerToUpdate.setLastName("Rocha");
        customerToUpdate.setDocType("2");
        customerToUpdate.setDocNumber("222222222");

        UpdateCustomer updateCustomer = new UpdateCustomer(customerRepository);
        updateCustomer.updateCustomer(customerToUpdate);

        Optional<CustomerModel> optionalCustomerModelUpdated = customerRepository.findByIdCustomer(String.valueOf(customerToUpdate.getIdCustomer()));

        Assertions.assertFalse(optionalCustomerModelUpdated.isPresent());

    }*/
}
