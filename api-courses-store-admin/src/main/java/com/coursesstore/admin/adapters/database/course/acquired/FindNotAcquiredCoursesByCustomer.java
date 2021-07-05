package com.coursesstore.admin.adapters.database.course.acquired;

import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.FindNotAcquiredCoursesByCustomerPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
public class FindNotAcquiredCoursesByCustomer implements FindNotAcquiredCoursesByCustomerPort {

    private final AcquiredCourseRepository acquiredCourseRepository;
    private final CourseRepository courseRepository;

    public FindNotAcquiredCoursesByCustomer(AcquiredCourseRepository acquiredCourseRepository,
                                            CourseRepository courseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public List<Course> findNotAcquiredCourses(String customerId) {
        Iterable<AcquiredCourseModel> listAcquiredCoursesModel = acquiredCourseRepository.findAll();
        Iterable<CourseModel> listCourseModels = courseRepository.findAll();

        List<Course> listNotAcquiredCourses = new ArrayList<>();

        for (CourseModel c : listCourseModels) {
            if(!StreamSupport.stream(listAcquiredCoursesModel.spliterator(),false).anyMatch(
                 acquiredCourseModel -> acquiredCourseModel.getCourse().getIdCourse().equals(c.getIdCourse())
            ))
                listNotAcquiredCourses.add(CourseConverter.toEntity(c));
        }

        return listNotAcquiredCourses;
    }
}
