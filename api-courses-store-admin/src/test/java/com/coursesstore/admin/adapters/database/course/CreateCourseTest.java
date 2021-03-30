package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.adapters.database.customer.model.CustomerConverter;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"spring.h2.console.enabled=true","server.port=8100"})
public class CreateCourseTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    @DisplayName("Given a valid Course domain object, When the Course is not in the database, Then create a new Course")
    public void Given_a_valid_Course_domain_object_When_the_Course_is_not_in_the_database_Then_create_a_new_Course() {

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
}