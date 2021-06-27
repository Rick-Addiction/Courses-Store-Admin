package com.coursesstore.admin.adapters.database.teacher;


import com.coursesstore.admin.adapters.database.teacher.model.TeacherConverter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    
}