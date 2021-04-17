package com.coursesstore.admin.adapters.http.customer.post;

import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8105"})
@AutoConfigureMockMvc
public class PostTeacherControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    @Autowired
    private MockMvc mockMvc;

    Course newCourse;

    @BeforeEach
    public void setUp(){
        Teacher newTeacher = registerANewTeacher();
        newCourse = registerANewCourse(newTeacher);
    }

    @Test
    @DisplayName("Given a valid Request to post a Customer, When its requested to create a Customer, Then response with the status CREATED")
    public void Given_a_valid_Request_to_post_a_Customer_When_its_requested_to_create_a_Customer_Then_response_with_the_status_CREATED() throws Exception {

        mockMvc.perform(
                post(REQUEST_PATH+ "/register")
                        .content(asJsonString(DomainUtils.generateRequestPostCustomer()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Given a valid Request to post an Acquired Course by Customer, When its requested to add a Acquired Course, Then response with the status CREATED")
    public void Given_a_valid_Request_to_post_an_Acquired_Course_by_Customer_When_its_requested_to_add_a_Acquired_Course_Then_response_with_the_status_CREATED() throws Exception {

        //Arrange
        Customer customer = registerANewCustomer();

        mockMvc.perform(
                post(REQUEST_PATH+ "/{id_customer}/acquire-course",String.valueOf(customer.getIdCustomer()))
                        .content(asJsonString(DomainUtils.generateRequestPostAcquiredCourse(String.valueOf(newCourse.getIdCourse()))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Given a valid Request to post an Desired Course by Customer, When its requested to add a Desired Course, Then response with the status CREATED")
    public void Given_a_valid_Request_to_post_an_Desired_Course_by_Customer_When_its_requested_to_add_a_Desired_Course_Then_response_with_the_status_CREATED() throws Exception {

        //Arrange
        Customer customer = registerANewCustomer();

        mockMvc.perform(
                post(REQUEST_PATH+ "/{id_customer}/desire-course",String.valueOf(customer.getIdCustomer()))
                        .content(asJsonString(DomainUtils.generateRequestPostDesiredCourse(String.valueOf(newCourse.getIdCourse()))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}