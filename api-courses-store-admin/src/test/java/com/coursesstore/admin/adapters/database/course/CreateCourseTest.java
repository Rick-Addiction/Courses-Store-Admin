package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class CreateCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid Course domain object, When the Course is not in the database, Then create a new Course")
    void Given_a_valid_Course_domain_object_When_the_Course_is_not_in_the_database_Then_create_a_new_Course() {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        ///Act
        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        ///Assert
        Optional<CourseModel> optionalCourseModel = courseRepository.findByIdCourse(String.valueOf(course.getIdCourse()));
        assertTrue(optionalCourseModel.isPresent());

        CourseModel courseModel = optionalCourseModel.get();
        Course courseCreated = CourseConverter.toEntity(courseModel);

        assertEquals(course.getIdCourse(),courseCreated.getIdCourse());
        assertEquals(course.getName(),courseCreated.getName());
        assertEquals(course.getOriginalValue(),courseCreated.getOriginalValue());
        assertEquals(course.getTeacherResponsible().getIdTeacher(),courseCreated.getTeacherResponsible().getIdTeacher());
        assertEquals(course.getTeacherResponsible().getName(),courseCreated.getTeacherResponsible().getName());

    }

    @Test
    @DisplayName("Given an invalid Course domain, When it is tried to create this Course, Then it will throw a ModelException")
    void Given_an_invalid_Course_domain_When_it_is_tried_to_create_this_Course_Then_it_will_throw_a_ModelException() {
        ///Act
        CreateCourse createCourse = new CreateCourse(courseRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> createCourse.createCourse(
                        null
                )
        );

        assertEquals("Conflict at the creating of a new Course: null",exception.getMessage());
    }
}