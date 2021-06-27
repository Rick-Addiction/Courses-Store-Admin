package com.coursesstore.admin.adapters.http.customer.put;

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
import static com.coursesstore.admin.adapters.AdapterUtils.registerANewCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8103"})
@AutoConfigureMockMvc
class PutCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    Customer customerToUpdate;

    @BeforeEach
    public void setUp(){
        customerToUpdate = registerANewCustomer();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to put a Customer, When its requested to update a Customer, Then response with the status OK")
    void Given_a_valid_Request_to_put_a_Customer_When_its_requested_to_update_a_Customer_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        RequestPutCustomer requestPutCustomer = DomainUtils.generateRequestPutCustomer(String.valueOf(customerToUpdate.getIdCustomer()));
        requestPutCustomer.setFirstname("Louis");

        ///Act
        mockMvc.perform(
                put(REQUEST_PATH+ "/update")
                        .content(asJsonString(requestPutCustomer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                ///Assert
                .andExpect(status().isOk());
    }
}