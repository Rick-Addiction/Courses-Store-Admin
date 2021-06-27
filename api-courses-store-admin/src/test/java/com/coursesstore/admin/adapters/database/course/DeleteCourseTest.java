package com.coursesstore.admin.adapters.database.course;


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

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class DeleteCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a Course which is stored in the database, When its requested to delete the Course, Then it should be done successfully")
    public void Given_a_Course_stored_in_the_database_When_its_requested_to_delete_the_Course_Then_it_should_be_done_successfully () {

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

}