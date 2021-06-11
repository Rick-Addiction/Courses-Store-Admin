package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.adapters.database.course.acquired.exception.AcquiredCourseConflictException;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddAcquiredCourse implements AddAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    private final CourseRepository courseRepository;

    public AddAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository,
                             CourseRepository courseRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public void addNewAcquiredCourseByCustomer(Customer customer) {
        try {

           Optional<CourseModel> courseModel = courseRepository.findByIdCourse(
                   String.valueOf(customer.getAcquiredCourses().iterator().next().getCourse().getIdCourse()));

            if(courseModel.isEmpty()){
                throw new ModelException("Course not found -  Customer " + String.valueOf(customer.getAcquiredCourses().iterator().next().getCourse().getIdCourse())+"!");
            }

            customer.getAcquiredCourses().iterator().next().setCourse(CourseConverter.toEntity(courseModel.get()));

            AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(customer);
            acquiredCourseRepository.save(acquiredCourseModel);
        } catch (Exception ex) {
            throw new AcquiredCourseConflictException();
        }
    }
}
