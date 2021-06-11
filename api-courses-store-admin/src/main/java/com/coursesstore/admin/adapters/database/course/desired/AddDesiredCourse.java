package com.coursesstore.admin.adapters.database.course.desired;



import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseConverter;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.adapters.database.course.desired.exception.DesiredCourseConflictException;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddDesiredCourse implements AddDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    private final CourseRepository courseRepository;

    public AddDesiredCourse(DesiredCourseRepository desiredCourseRepository,
                            CourseRepository courseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public void addNewDesiredCourseByCustomer(Customer customer) {
        try {

            Optional<CourseModel> courseModel = courseRepository.findByIdCourse(
                    String.valueOf(customer.getDesiredCourses().iterator().next().getCourse().getIdCourse()));

            if(courseModel.isEmpty()){
                throw new ModelException("Course not found -  Customer " + String.valueOf(customer.getAcquiredCourses().iterator().next().getCourse().getIdCourse())+"!");
            }

            customer.getDesiredCourses().iterator().next().setCourse(CourseConverter.toEntity(courseModel.get()));

            DesiredCourseModel desiredCourseModel = DesiredCourseConverter.toModel(customer);
            desiredCourseRepository.save(desiredCourseModel);
        } catch (Exception ex) {
            throw new DesiredCourseConflictException();
        }
    }
}
