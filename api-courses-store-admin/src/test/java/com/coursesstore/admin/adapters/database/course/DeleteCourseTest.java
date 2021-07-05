package com.coursesstore.admin.adapters.database.course;


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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
class DeleteCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a Course which is stored in the database, When its requested to delete the Course, Then it should be done successfully")
    void Given_a_Course_stored_in_the_database_When_its_requested_to_delete_the_Course_Then_it_should_be_done_successfully () {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        Optional<CourseModel> optionalCourseModel = courseRepository.findByIdCourse(String.valueOf(course.getIdCourse()));

        assertTrue(optionalCourseModel.isPresent());

        Course courseToBeDeleted = new Course();
        courseToBeDeleted.setIdCourse(course.getIdCourse());

        ///Act
        DeleteCourse deleteCourse = new DeleteCourse(courseRepository);
        deleteCourse.deleteCourse(String.valueOf(courseToBeDeleted.getIdCourse()));

        ///Assert
        Optional<CourseModel> optionalDeletedCourseModel = courseRepository.findByIdCourse(String.valueOf(course.getIdCourse()));
        assertTrue(optionalDeletedCourseModel.isEmpty());

    }

    @Test
    @DisplayName("Given an invalid Course domain, When it is tried to delete this Course, Then it will throw a ModelException")
    void Given_an_invalid_Course_domain_When_it_is_tried_to_delete_this_Course_Then_it_will_throw_a_ModelException() {
        ///Act
        DeleteCourse deleteCourse = new DeleteCourse(courseRepository);

        ///Assert
        ModelException exception = assertThrows(
                ModelException.class,
                () -> deleteCourse.deleteCourse(
                        "d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b"
                )
        );

        assertEquals("Course not found -  Course d6b0c519-d1ad-480c-b190-cc1f5e3f8d4b!",exception.getMessage());
    }

}