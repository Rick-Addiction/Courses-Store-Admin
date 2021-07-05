package com.coursesstore.admin.adapters.database.teacher;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class CreateTeacherTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid Teacher domain object, When the Teacher is not in the database, Then create a new Teacher")
    void Given_a_valid_Teacher_domain_object_When_the_Teacher_is_not_in_the_database_Then_create_a_new_Teacher() {

        Teacher teacher = DomainUtils.generateTeacher();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(teacher);

        Optional<TeacherModel> optionalTeacherModel = teacherRepository.findByIdTeacher(String.valueOf(teacher.getIdTeacher()));

        assertTrue(optionalTeacherModel.isPresent());

        TeacherModel teacherModel = optionalTeacherModel.get();
        Teacher teacherCreated = TeacherConverter.toEntity(teacherModel);

        assertEquals(teacher.getIdTeacher(),teacherCreated.getIdTeacher());
        assertEquals(teacher.getName(),teacherCreated.getName());
    }

    @Test
    @DisplayName("Given an invalid Teacher domain, When it is tried to create this Teacher, Then it will throw a ModelException")
    void Given_an_invalid_Teacher_domain_When_it_is_tried_to_create_this_Teacher_Then_it_will_throw_a_ModelException() {
        ///Act
        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> createTeacher.createTeacher(
                        null
                )
        );

        assertEquals("Conflict at the creating of a new Teacher: null",exception.getMessage());
    }

}