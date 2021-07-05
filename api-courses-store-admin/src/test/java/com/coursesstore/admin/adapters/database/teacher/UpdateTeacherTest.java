package com.coursesstore.admin.adapters.database.teacher;


import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.UpdateCourse;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8101"})
class UpdateTeacherTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid Teacher stored in the database, When its requested to update the Teacher, Then it should be done successfully")
    void Given_a_valid_Teacher_stored_in_the_database_When_its_requested_to_update_the_Teacher_Then_it_should_be_done_successfully() {

        ///Arrange
        Teacher teacher = DomainUtils.generateTeacher();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacher);

        Optional<TeacherModel> teacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher()));

        assertTrue(teacherModel.isPresent());

        ///Act
        var teacherToUpdate = teacher;
        teacherToUpdate.setName("Ellie");

        UpdateTeacher updateTeacher = new UpdateTeacher(teacherRepository);
        updateTeacher.updateTeacher(teacherToUpdate);

        ///Assert
        Optional<TeacherModel> optionalTeacherModelUpdated = teacherRepository.findByIdTeacher(String.valueOf(teacherToUpdate.getIdTeacher()));

        assertTrue(optionalTeacherModelUpdated.isPresent());

        TeacherModel teacherModelUpdated = optionalTeacherModelUpdated.get();
        Teacher teacherUpdated = TeacherConverter.toEntity(teacherModelUpdated);

        assertEquals(teacherUpdated.getIdTeacher(), teacherToUpdate.getIdTeacher());
        assertEquals(teacherUpdated.getName(),teacherToUpdate.getName());
    }

    @Test
    @DisplayName("Given an invalid Teacher domain, When it is tried to update this Teacher, Then it will throw a ModelException")
    void Given_an_invalid_Teacher_domain_When_it_is_tried_to_update_this_Teacher_Then_it_will_throw_a_ModelException() {
        ///Arrange
        Teacher teacher = DomainUtils.generateTeacher();
        teacher.setIdTeacher(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        ///Act
        UpdateTeacher updateTeacher = new UpdateTeacher(teacherRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> updateTeacher.updateTeacher(
                        teacher
                )
        );

        assertEquals("Teacher not found -  Teacher d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }
    
}