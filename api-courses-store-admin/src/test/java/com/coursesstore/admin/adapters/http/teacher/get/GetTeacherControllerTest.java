package com.coursesstore.admin.adapters.http.teacher.get;

import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8102"})
@AutoConfigureMockMvc
public class GetTeacherControllerTest {

    final String REQUEST_PATH = "/courses-store/teacher";

    @Autowired
    private TeacherRepository teacherRepository;

    @BeforeEach
    public void setUp(){
        Teacher teacher = DomainUtils.generateTeacher();
        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacher);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to get a Teacher, When its requested for a Teacher, Then response with the expected Teacher")
    public void Given_a_valid_Request_to_get_a_Teacher_When_its_requested_for_a_Teacher_Then_response_with_the_expected_Teacher() throws Exception {

        mockMvc.perform(
                get(REQUEST_PATH+ "/search")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teachers[0].idTeacher").exists())
                .andExpect(jsonPath("$.teachers[0].name").value("Joel"));
    }
}