package com.coursesstore.admin.adapters.http.course.put;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutCourse;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8104"})
@AutoConfigureMockMvc
public class PutCourseControllerTest {

    final String REQUEST_PATH = "/courses-store/course";

    Customer newCustomer;
    Course courseToUpdate;
    Teacher newTeacher;
    AcquiredCourse acquiredCourseToUpdate;
    DesiredCourse desiredCourseToUpdate;

    @BeforeEach
    public void setUp(){
        newCustomer = registerANewCustomer();
        newTeacher = registerANewTeacher();
        courseToUpdate = registerANewCourse(newTeacher);
        newCustomer = registerANewAcquiredCourse(newCustomer, courseToUpdate);
        newCustomer = registerANewDesiredCourse(newCustomer, courseToUpdate);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid Request to put a Course, When its requested to update a Course, Then response with the status OK")
    public void Given_a_valid_Request_to_put_a_Course_When_its_requested_to_update_a_Course_Then_response_with_the_status_OK() throws Exception {

        ///Arrange
        RequestPutCourse requestPutCourse = DomainUtils.generateRequestPutCourse(
                String.valueOf(courseToUpdate.getIdCourse()),
                String.valueOf(newTeacher.getIdTeacher()));
        requestPutCourse.setName("C++ Advanced");

        ///Act
        mockMvc.perform(
                put(REQUEST_PATH+ "/update")
                        .content(asJsonString(requestPutCourse))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))

                ///Assert
                .andExpect(status().isOk());
    }

}