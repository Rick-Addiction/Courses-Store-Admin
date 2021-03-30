package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.adapters.database.teacher.DeleteTeacher;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
public class DeleteTeacherTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a Teacher which is stored in the database, When its requested to delete the Teacher, Then it should be done successfully")
    public void Given_a_Teacher_stored_in_the_database_When_its_requested_to_delete_the_Teacher_Then_it_should_be_done_successfully () {

        ///Arrange
        Teacher teacher = DomainUtils.generateTeacher();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacher);

        Optional<TeacherModel> optionalCreatedTeacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher()));

        assertTrue(optionalCreatedTeacherModel.isPresent());

        Teacher teacherToBeDeleted = new Teacher();
        teacherToBeDeleted.setIdTeacher(teacher.getIdTeacher());

        ///Act
        DeleteTeacher deleteTeacher = new DeleteTeacher(teacherRepository);
        deleteTeacher.deleteTeacher(teacherToBeDeleted);

        ///Assert
        Optional<TeacherModel> optionalDeletedTeacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher()));
        assertTrue(optionalDeletedTeacherModel.isEmpty());
    }
    
}