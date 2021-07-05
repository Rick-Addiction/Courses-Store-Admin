package com.coursesstore.admin.adapters.http.course.get;

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

import static com.coursesstore.admin.adapters.AdapterUtils.registerANewCourse;
import static com.coursesstore.admin.adapters.AdapterUtils.registerANewCustomer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8102"})
@AutoConfigureMockMvc
class GetCourseControllerTest {

    final String REQUEST_PATH = "/courses-store/course";

    Course newCourse;

    @BeforeEach
    public void setUp(){
        newCourse = registerANewCourse();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to get a Course, When its requested for a Course, Then response with the expected Course")
    void Given_a_valid_Request_to_get_a_Course_When_its_requested_for_a_Course_Then_response_with_the_expected_Course() throws Exception {

        mockMvc.perform(
                get(REQUEST_PATH+ "/search")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courses[0].id_course").exists())
                .andExpect(jsonPath("$.courses[0].name").value(newCourse.getName()))
                .andExpect(jsonPath("$.courses[0].original_value").value(newCourse.getOriginalValue()))
                .andExpect(jsonPath("$.courses[0].id_teacher_responsible").value(String.valueOf(newCourse.getTeacherResponsible().getIdTeacher())))
                .andExpect(jsonPath("$.courses[0].teacher_responsible_name").value(newCourse.getTeacherResponsible().getName()));
    }

}