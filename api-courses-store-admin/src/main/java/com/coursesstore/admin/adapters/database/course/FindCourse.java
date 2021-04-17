package com.coursesstore.admin.adapters.database.course;

import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.FindCoursePort;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class FindCourse implements FindCoursePort {

    private final CourseRepository CourseRepository;

    public FindCourse(CourseRepository CourseRepository){
        this.CourseRepository=CourseRepository;
    }


    private static final Logger log = LoggerFactory.getLogger(FindCourse.class);

    @Override
    public List<Course>  findCourse(String courseSearchValues) {
        Iterable<CourseModel> listCoursesModel;

        //TODO Refine the search for Courses

        listCoursesModel = CourseRepository.findAll();

        List<Course> listCourses = new ArrayList<>();

        for (CourseModel c : listCoursesModel) {
            Course course = new Course();
            course.setIdCourse(UUID.fromString(c.getIdCourse()));
            course.setName(c.getName());
            course.setOriginalValue(c.getOriginalValue());

            Teacher teacherResponsible = new Teacher();
            teacherResponsible.setIdTeacher(UUID.fromString(c.getTeacherResponsible().getIdTeacher()));
            teacherResponsible.setName(c.getTeacherResponsible().getName());
            course.setTeacherResponsible(teacherResponsible);

            listCourses.add(course);
        }

        return listCourses;
    }
}
