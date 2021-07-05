package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.FindNotDesiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class FindNotDesiredCoursesByCustomer implements FindNotDesiredCoursesByCustomerPort {

    private final DesiredCourseRepository desiredCourseRepository;
    private final CourseRepository courseRepository;

    public FindNotDesiredCoursesByCustomer(DesiredCourseRepository desiredCourseRepository,
                                           CourseRepository courseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public List<Course> findNotDesiredCourses(String customerId) {
        Iterable<DesiredCourseModel> listDesiredCoursesModel = desiredCourseRepository.findAll();
        Iterable<CourseModel> listCourseModels = courseRepository.findAll();

        List<Course> listNotDesiredCourses = new ArrayList<>();

        for (CourseModel c : listCourseModels) {
            if(!StreamSupport.stream(listDesiredCoursesModel.spliterator(),false).anyMatch(
                 desiredCourseModel -> desiredCourseModel.getCourse().getIdCourse().equals(c.getIdCourse())
            ))
                listNotDesiredCourses.add(CourseConverter.toEntity(c));
        }

        return listNotDesiredCourses;
    }
}
