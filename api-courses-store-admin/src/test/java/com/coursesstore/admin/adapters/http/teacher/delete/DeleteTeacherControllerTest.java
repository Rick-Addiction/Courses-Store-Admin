package com.coursesstore.admin.adapters.http.teacher.delete;

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

import static com.coursesstore.admin.adapters.AdapterUtils.registerANewTeacher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8109"})
@AutoConfigureMockMvc
public class DeleteTeacherControllerTest {

    final String REQUEST_PATH = "/courses-store/teacher";

    Teacher teacherCreated;


    @BeforeEach
    public void setUp(){
        teacherCreated = registerANewTeacher();
      }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to delete a Teacher, When its requested to delete a Teacher, Then response with the status OK")
    public void Given_a_valid_Request_to_delete_a_Teacher_When_its_requested_to_delete_a_Teacher_Then_response_with_the_status_OK() throws Exception {

        ///Act
        mockMvc.perform(
                delete(REQUEST_PATH+ "/{id_teacher}",String.valueOf(teacherCreated.getIdTeacher()))
                        .accept(MediaType.APPLICATION_JSON))
                ///Assert
                .andExpect(status().isOk());
    }
}