package com.coursesstore.admin.adapters.http.customer.get;

import com.coursesstore.admin.adapters.AdapterUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.coursesstore.admin.adapters.AdapterUtils.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.h2.console.enabled=true"})
@AutoConfigureMockMvc
@TestInstance(PER_CLASS)
class GetCustomerControllerTest {

    final String REQUEST_PATH = "/courses-store/customer";

    static List<Course> courses;

    @BeforeAll
    public void setUp(){
        courses = List.of(
                registerANewCourse(),
                registerANewCourse()
        );
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to get all Customers, When its requested to get all Customers, Then response with the expected Customers")
    void Given_a_valid_Request_to_get_all_Customers_When_its_requested_to_get_all_Customers_Then_response_with_the_expected_Customers() throws Exception {

        ///Arrange
        Customer newCustomer = AdapterUtils.registerANewCustomer();

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

    @Test
    @DisplayName("Given a valid Request to get a Customer, When its requested for a Customer, Then response with the expected Customer")
    void Given_a_valid_Request_to_get_a_Customer_When_its_requested_for_a_Customer_Then_response_with_the_expected_Customer() throws Exception {

        ///Arrange
        Customer newCustomer = AdapterUtils.registerANewCustomer();

        mockMvc.perform(
                get(REQUEST_PATH+ "/search/{id_customer}",String.valueOf(newCustomer.getIdCustomer()))
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

    @Test
    @DisplayName("Given a valid Request to get the Acquired Courses and Not Acquired Courses of a Customer, When its requested  get them, Then response with the expected data")
    void Given_a_valid_Request_to_get_the_Acquired_Courses_and_Not_Acquired_Courses_of_a_Customer_When_its_requested__get_them_Then_response_with_the_expected_data() throws Exception {

        ///Arrange
        var newCustomer = AdapterUtils.registerANewCustomer();
        var idCustomer = String.valueOf(newCustomer.getIdCustomer());
        AcquiredCourse acquiredCourse = registerANewAcquiredCourse(idCustomer,courses.get(0));


        mockMvc.perform(
                get(REQUEST_PATH+ "/{id_customer}/acquired-courses",idCustomer)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.acquired_courses[0].id_acquired_course").value(String.valueOf(acquiredCourse.getCourse().getIdCourse())))
                .andExpect(jsonPath("$.acquired_courses[0].course_name").value(acquiredCourse.getCourse().getName()))
                .andExpect(jsonPath("$.acquired_courses[0].acquisition_date").value(String.valueOf(acquiredCourse.getAcquisitionDate())))
                .andExpect(jsonPath("$.acquired_courses[0].value_paid").value(acquiredCourse.getValuePaid()))
                .andExpect(jsonPath("$.not_acquired_courses[0].id_course").value(String.valueOf(courses.get(1).getIdCourse())))
                .andExpect(jsonPath("$.not_acquired_courses[0].name").value(courses.get(1).getName()));
    }

    @Test
    @DisplayName("Given a valid Request to get the Desired Courses and Not Desired Courses of a Customer, When its requested  get them, Then response with the expected data")
    void Given_a_valid_Request_to_get_the_Desired_Courses_and_Not_Desired_Courses_of_a_Customer_When_its_requested__get_them_Then_response_with_the_expected_data() throws Exception {

        ///Arrange
        var newCustomer = AdapterUtils.registerANewCustomer();
        var idCustomer = String.valueOf(newCustomer.getIdCustomer());
        DesiredCourse desiredCourse = registerANewDesiredCourse(idCustomer,courses.get(0));

        mockMvc.perform(
                get(REQUEST_PATH+ "/{id_customer}/desired-courses",idCustomer)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.desired_courses[0].id_desired_course").value(String.valueOf(desiredCourse.getCourse().getIdCourse())))
                .andExpect(jsonPath("$.desired_courses[0].course_name").value(desiredCourse.getCourse().getName()))
                .andExpect(jsonPath("$.desired_courses[0].desire_date").value(String.valueOf(desiredCourse.getDesireDate())))
                .andExpect(jsonPath("$.desired_courses[0].desire_description").value(desiredCourse.getDesireDescription()))
                .andExpect(jsonPath("$.not_desired_courses[0].id_course").value(String.valueOf(courses.get(1).getIdCourse())))
                .andExpect(jsonPath("$.not_desired_courses[0].name").value(courses.get(1).getName()));
    }
}