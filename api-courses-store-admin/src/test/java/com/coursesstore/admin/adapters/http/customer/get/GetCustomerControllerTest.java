package com.coursesstore.admin.adapters.http.customer.get;

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

import static com.coursesstore.admin.adapters.AdapterUtils.registerANewCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8102"})
@AutoConfigureMockMvc
class GetCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    Customer newCustomer;

    @BeforeEach
    public void setUp(){
        newCustomer = registerANewCustomer();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to get a Customer, When its requested for a Customer, Then response with the expected Customer")
    void Given_a_valid_Request_to_get_a_Customer_When_its_requested_for_a_Customer_Then_response_with_the_expected_Customer() throws Exception {

        mockMvc.perform(
                get(REQUEST_PATH+ "/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers[0].idCustomer").exists())
                .andExpect(jsonPath("$.customers[0].firstname").value(newCustomer.getFirstname()))
                .andExpect(jsonPath("$.customers[0].lastname").value(newCustomer.getLastname()))
                .andExpect(jsonPath("$.customers[0].phone").value(newCustomer.getPhone()))
                .andExpect(jsonPath("$.customers[0].email").value(newCustomer.getEmail()))
                .andExpect(jsonPath("$.customers[0].linkedin").value(newCustomer.getLinkedIn()))
                .andExpect(jsonPath("$.customers[0].company").value(newCustomer.getCompany()))
                .andExpect(jsonPath("$.customers[0].position").value(newCustomer.getPosition()));
    }
}