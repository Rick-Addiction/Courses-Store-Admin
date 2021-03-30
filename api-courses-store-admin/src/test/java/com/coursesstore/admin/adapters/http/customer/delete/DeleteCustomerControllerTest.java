package com.coursesstore.admin.adapters.http.customer.delete;

import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.coursesstore.admin.adapters.AdapterUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8109"})
@AutoConfigureMockMvc
public class DeleteCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    @Autowired
    private CustomerRepository customerRepository;

    String idCustomerCreated;

    @BeforeEach
    public void setUp(){
        Customer customer = DomainUtils.generateCustomer();
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        idCustomerCreated = createCustomer.createCustomer(customer);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to delete a Customer, When its requested to delete a Customer, Then response with the status OK")
    public void Given_a_valid_Request_to_delete_a_Customer_When_its_requested_to_delete_a_Customer_Then_response_with_the_status_OK() throws Exception {

        ///Act
        mockMvc.perform(
                delete(REQUEST_PATH+ "/delete/{id_customer}",idCustomerCreated)
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

}