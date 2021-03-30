package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.AddAcquiredCoursePort;
import com.coursesstore.admin.adapters.database.course.acquired.exception.AcquiredCourseConflictException;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddAcquiredCourse implements AddAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;

    private final CustomerRepository customerRepository;

    public AddAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository,
                             CustomerRepository customerRepository){
        this.acquiredCourseRepository=acquiredCourseRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public void addNewAcquiredCourseByCustomer(Customer customer) {
        try {
            AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(customer);
            acquiredCourseRepository.save(acquiredCourseModel);
        } catch (Exception ex) {
            throw new AcquiredCourseConflictException();
        }
    }
}
