package com.coursesstore.admin.adapters.http.customer.get;

import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8102"})
@AutoConfigureMockMvc
public class GetCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp(){
        Customer customer = DomainUtils.generateCustomer();
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to get a Customer, When its requested for a Customer, Then response with the expected Customer")
    public void Given_a_valid_Request_to_get_a_Customer_When_its_requested_for_a_Customer_Then_response_with_the_expected_Customer() throws Exception {

        mockMvc.perform(
                get(REQUEST_PATH+ "/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCustomer").exists())
                .andExpect(jsonPath("$.firstname").value("Dina"))
                .andExpect(jsonPath("$.lastname").value("Laster"))
                .andExpect(jsonPath("$.phone").value("+55 11 99999-9999"))
                .andExpect(jsonPath("$.email").value("email_test@testdomain.com"))
                .andExpect(jsonPath("$.linkedIn").value("linkedIn.com/DinaLaster"))
                .andExpect(jsonPath("$.company").value("Robots with Love"))
                .andExpect(jsonPath("$.position").value("CEO"));
    }
}