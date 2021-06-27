package com.coursesstore.admin.adapters.database.course.acquired;


import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.acquired.UpdateAcquiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateAcquiredCourse implements UpdateAcquiredCoursePort {

    private final AcquiredCourseRepository acquiredCourseRepository;
    private final CustomerRepository customerRepository;

    public UpdateAcquiredCourse(AcquiredCourseRepository acquiredCourseRepository,
                                CustomerRepository customerRepository) {
        this.acquiredCourseRepository = acquiredCourseRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public void updateAcquiredCourse(String idCustomer, AcquiredCourse acquiredCourse){


        Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(idCustomer);

        if(customerModel.isEmpty()){
            throw new ModelException("Customer not found -  Customer " + idCustomer +"!");
        }

        AcquiredCourseModel acquiredCourseModel = AcquiredCourseConverter.toModel(customerModel.get(),acquiredCourse);

        acquiredCourseRepository.save(acquiredCourseModel);
    }
}
