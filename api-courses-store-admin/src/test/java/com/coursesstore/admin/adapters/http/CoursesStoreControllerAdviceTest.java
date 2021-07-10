package com.coursesstore.admin.adapters.http;

import com.coursesstore.admin.adapters.http.customer.post.dto.RequestPostCustomer;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.coursesstore.admin.adapters.AdapterUtils.asJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8105"})
@AutoConfigureMockMvc
class CoursesStoreControllerAdviceTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Given the thrown of ConstraintViolationException, When its called the ControllerAdvice to handle the exception, Then it must response respectively")
    void Given_the_thrown_of_ConstraintViolationException_When_its_called_the_ControllerAdvice_to_handle_the_exception_Then_it_must_response_respectively() throws Exception {

        ///Arrange
        RequestPostCustomer requestPostCustomer = DomainUtils.generateRequestPostCustomer();
        requestPostCustomer.setFirstname(null);

        ///Act & Assert
        mockMvc.perform(
                post("/courses-store/customer/register")
                        .content(asJsonString(requestPostCustomer))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("firstname: must not be blank"));
    }

    @Test
    @DisplayName("Given the thrown of DataNotFoundException, When its called the ControllerAdvice to handle the exception, Then it must response respectively")
    void Given_the_thrown_of_DataNotFoundException_When_its_called_the_ControllerAdvice_to_handle_the_exception_Then_it_must_response_respectively() throws Exception {

        ///Arrange, Act & Assert
        mockMvc.perform(
                get("/courses-store/customer/search/1234")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Customer not found -  Customer 1234!"));
    }

    @Test
    @DisplayName("Given the thrown of ModelException, When its called the ControllerAdvice to handle the exception, Then it must response respectively")
    void Given_the_thrown_of_ModelException_When_its_called_the_ControllerAdvice_to_handle_the_exception_Then_it_must_response_respectively() throws Exception {

        ///Arrange
        String idCourse = "1d69bba3-bdc6-4ace-b132-5a3e9400546b";

        ///Act & Assert
        mockMvc.perform(
                post("/courses-store/customer/{id_customer}}/acquire-course",String.valueOf(UUID.randomUUID()))
                .content(asJsonString(DomainUtils.generateRequestPostAcquiredCourse(idCourse)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Conflict at the adding of a new Acquired Course: Course not found -  Course 1d69bba3-bdc6-4ace-b132-5a3e9400546b!"));

    }
    @Test
    @DisplayName("Given the thrown of ControllerAdviceException, When its called the ControllerAdvice to handle the exception, Then it must response respectively")
    void Given_the_thrown_of_ControllerAdviceException_When_its_called_the_ControllerAdvice_to_handle_the_exception_Then_it_must_response_respectively() throws Exception {

        ControllerAdviceException exception = assertThrows(
                ControllerAdviceException.class,
                () -> CoursesStoreControllerAdvice.getJSONPropertyName(Customer.class,"test"));

        assertEquals("Controller Advice Error - Field test not find on the class Customer",
                exception.getMessage());

    }

}