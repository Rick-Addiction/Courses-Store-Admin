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

import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class FindCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid id of an Course stored in the database, When its searched for this Course, Then return the Course searched")
    public void Given_a_valid_id_of_an_Course_stored_in_the_database_When_its_searched_for_this_Course_Then_return_the_Course_searched() {

        ///Arrange
        Course course = DomainUtils.generateCourse();

        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(course.getTeacherResponsible());

        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(course);

        ///Act
        FindCourse findCourse = new FindCourse(courseRepository);

        ///Assert
        //TODO Set verify
    }

}