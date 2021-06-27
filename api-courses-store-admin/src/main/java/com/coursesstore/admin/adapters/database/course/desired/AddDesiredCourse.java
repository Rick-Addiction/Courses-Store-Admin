package com.coursesstore.admin.adapters.database.course.desired;


import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.desired.exception.DesiredCourseConflictException;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddDesiredCourse implements AddDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;
    private final CustomerRepository customerRepository;
    private final CourseRepository courseRepository;

    public AddDesiredCourse(DesiredCourseRepository desiredCourseRepository,
                            CustomerRepository customerRepository,
                            CourseRepository courseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
        this.customerRepository=customerRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public void addNewDesiredCourseByCustomer(String idCustomer, DesiredCourse desiredCourse) {
        try {

            String idCourse = String.valueOf(desiredCourse.getCourse().getIdCourse());

            Optional<CourseModel> courseModel = courseRepository.findByIdCourse(idCourse);

            if(courseModel.isEmpty()){
                throw new ModelException("Course not found -  Course " + idCourse +"!");
            }

            Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(idCustomer);

            if(customerModel.isEmpty()){
                throw new ModelException("Customer not found -  Customer " + idCustomer +"!");
            }

            DesiredCourseModel desiredCourseModel = DesiredCourseConverter.toModel(customerModel.get(),desiredCourse);
            desiredCourseRepository.save(desiredCourseModel);
        } catch (Exception ex) {
            throw new DesiredCourseConflictException();
        }
    }
}
