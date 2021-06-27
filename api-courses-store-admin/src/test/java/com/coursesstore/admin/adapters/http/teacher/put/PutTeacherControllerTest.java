package com.coursesstore.admin.adapters.http.teacher.put;


import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.adapters.http.teacher.put.dto.RequestPutTeacher;
import com.coursesstore.admin.core.domain.DomainUtils;
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

import static com.coursesstore.admin.adapters.AdapterUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8103"})
@AutoConfigureMockMvc
public class PutTeacherControllerTest {

    final String REQUEST_PATH = "/courses-store/teacher";

    @Autowired
    private TeacherRepository teacherRepository;

    Teacher teacherToUpdate;

    @BeforeEach
    public void setUp(){
        teacherToUpdate = DomainUtils.generateTeacher();
        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacherToUpdate);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to put a Teacher, When its requested to update a Teacher, Then response with the status OK")
    public void Given_a_valid_Request_to_put_a_Teacher_When_its_requested_to_update_a_Teacher_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        RequestPutTeacher requestPutTeacher = DomainUtils.generateRequestPutTeacher(String.valueOf(teacherToUpdate.getIdTeacher()));
        requestPutTeacher.setName("Louis");

        ///Act
        mockMvc.perform(
                put(REQUEST_PATH+ "/update")
                        .content(asJsonString(requestPutTeacher))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                ///Assert
                .andExpect(status().isOk());
    }
}