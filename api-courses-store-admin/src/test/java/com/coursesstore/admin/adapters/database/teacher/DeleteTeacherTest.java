package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.DeleteCourse;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
class DeleteTeacherTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a Teacher which is stored in the database, When its requested to delete the Teacher, Then it should be done successfully")
    void Given_a_Teacher_stored_in_the_database_When_its_requested_to_delete_the_Teacher_Then_it_should_be_done_successfully () {

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
        deleteTeacher.deleteTeacher(String.valueOf(teacherToBeDeleted.getIdTeacher()));

        ///Assert
        Optional<TeacherModel> optionalDeletedTeacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher()));
        assertTrue(optionalDeletedTeacherModel.isEmpty());
    }

    @Test
    @DisplayName("Given an invalid Teacher domain, When it is tried to delete this Teacher, Then it will throw a ModelException")
    void Given_an_invalid_Teacher_domain_When_it_is_tried_to_delete_this_Teacher_Then_it_will_throw_a_ModelException() {
        ///Act
        DeleteTeacher deleteTeacher = new DeleteTeacher(teacherRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> deleteTeacher.deleteTeacher(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"
                )
        );

        assertEquals("Teacher not found - Teacher d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
    
}