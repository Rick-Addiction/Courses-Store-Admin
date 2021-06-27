package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.exception.AcquiredCourseConflictException;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddAcquiredCourse implements AddAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;
    private final CustomerRepository customerRepository;
    private final CourseRepository courseRepository;

    public AddAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository,
                             CustomerRepository customerRepository,
                             CourseRepository courseRepository
                             ){
        this.acquiredCourseRepository=acquiredCourseRepository;
        this.courseRepository=courseRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public void addNewAcquiredCourseByCustomer(String idCustomer, AcquiredCourse acquiredCourse) {
        try {

            var idCourse = String.valueOf(acquiredCourse.getCourse().getIdCourse());
            Optional<CourseModel> courseModel = courseRepository.findByIdCourse(idCourse);

            if(courseModel.isEmpty()){
                throw new ModelException("Course not found -  Course " + idCourse +"!");
            }

            Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(idCustomer);

            if(customerModel.isEmpty()){
                throw new ModelException("Customer not found -  Customer " + idCustomer +"!");
            }

            var acquiredCourseModel = AcquiredCourseConverter.toModel(customerModel.get(),acquiredCourse);
            acquiredCourseRepository.save(acquiredCourseModel);
        } catch (Exception ex) {
            throw new AcquiredCourseConflictException();
        }
    }
}
