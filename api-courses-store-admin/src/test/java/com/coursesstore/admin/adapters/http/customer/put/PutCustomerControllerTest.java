package com.coursesstore.admin.adapters.http.customer.put;

import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutAcquiredCourse;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutDesiredCourse;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
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

import static com.coursesstore.admin.adapters.AdapterUtils.*;
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

    @Test
    @DisplayName("Given a valid Request to update an Acquired Course, When its requested to update this Acquired Course, Then response with the status OK")
    void Given_a_valid_Request_to_update_an_Acquired_Course_When_its_requested_to_update_this_Acquired_Course_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        String idCustomer = String.valueOf(registerANewCustomer().getIdCustomer());
        Course course = registerANewCourse();
        registerANewAcquiredCourse(idCustomer,course);

        RequestPutAcquiredCourse requestPutAcquiredCourse = DomainUtils.generateRequestPutAcquiredCourse(
                idCustomer, String.valueOf(course.getIdCourse())
        );
        requestPutAcquiredCourse.setValuePaid("6000.50");


        ///Act
        mockMvc.perform(
                put(REQUEST_PATH+ "/acquired/update")
                        .content(asJsonString(requestPutAcquiredCourse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Given a valid Request to update an Desired Course, When its requested to update this Desired Course, Then response with the status OK")
    void Given_a_valid_Request_to_update_an_Desired_Course_When_its_requested_to_update_this_Desired_Course_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        String idCustomer = String.valueOf(registerANewCustomer().getIdCustomer());
        Course course = registerANewCourse();
        registerANewDesiredCourse(idCustomer,course);

        RequestPutDesiredCourse requestPutDesiredCourse = DomainUtils.generateRequestPutDesiredCourse(
                idCustomer, String.valueOf(course.getIdCourse())
        );
        requestPutDesiredCourse.setDesireDescription("DESCRIPTION UPDATED");


        ///Act
        mockMvc.perform(
                put(REQUEST_PATH+ "/desired/update")
                        .content(asJsonString(requestPutDesiredCourse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }
}