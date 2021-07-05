package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.DataNotFoundException;
import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
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
class UpdateCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid Course stored in the database, When its requested to update the Course, Then it should be done successfully")
    void Given_a_valid_Course_stored_in_the_database_When_its_requested_to_update_the_Course_Then_it_should_be_done_successfully() {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        Optional<CourseModel> courseModel = courseRepository.findByIdCourse(
                String.valueOf(course.getIdCourse()));

        assertTrue(courseModel.isPresent());

        ///Act
        Course courseToUpdate = course;
        courseToUpdate.setName("C++ Advanced");

        UpdateCourse updateCourse = new UpdateCourse(courseRepository);
        updateCourse.updateCourse(courseToUpdate);

        ///Assert
        Optional<CourseModel> optionalCourseModelUpdated = courseRepository.findByIdCourse(
                String.valueOf(courseToUpdate.getIdCourse()));

        assertTrue(optionalCourseModelUpdated.isPresent());

        CourseModel courseModelUpdated = optionalCourseModelUpdated.get();
        Course courseUpdated = CourseConverter.toEntity(courseModelUpdated);

        assertEquals(courseUpdated.getIdCourse(), courseToUpdate.getIdCourse());
        assertEquals(courseUpdated.getName(),courseToUpdate.getName());
        assertEquals(courseUpdated.getOriginalValue(),courseToUpdate.getOriginalValue());
        assertEquals(courseUpdated.getTeacherResponsible().getIdTeacher(),courseToUpdate.getTeacherResponsible().getIdTeacher());
        assertEquals(courseUpdated.getTeacherResponsible().getName(),courseToUpdate.getTeacherResponsible().getName());

    }

    @Test
    @DisplayName("Given an invalid Course domain, When it is tried to update this Course, Then it will throw a ModelException")
    void Given_an_invalid_Course_domain_When_it_is_tried_to_update_this_Course_Then_it_will_throw_a_ModelException() {
        ///Arrange
        Course course = DomainUtils.generateCourse();
        course.setIdCourse(UUID.fromString("d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"));

        ///Act
        UpdateCourse updateCourse = new UpdateCourse(courseRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> updateCourse.updateCourse(
                        course
                )
        );

        assertEquals("Course not found -  Course d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }

}