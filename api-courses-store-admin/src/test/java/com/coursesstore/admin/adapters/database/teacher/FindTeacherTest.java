package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.course.FindCourse;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
class FindTeacherTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an Teacher stored in the database, When its searched for this Customer, Then return the Teacher searched")
    void Given_a_valid_id_of_an_Teacher_stored_in_the_database_When_its_searched_for_this_Customer_Then_return_the_Teacher_searched() {

        ///Arrange
        Teacher teacher = DomainUtils.generateTeacher();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacher);

        ///Act
        FindTeacher findTeacher = new FindTeacher(teacherRepository);

        var teachersList = findTeacher.findTeacher(
                String.valueOf(teacher.getIdTeacher())
        );

        ///Assert
        assertNotNull(teachersList);

    }
}