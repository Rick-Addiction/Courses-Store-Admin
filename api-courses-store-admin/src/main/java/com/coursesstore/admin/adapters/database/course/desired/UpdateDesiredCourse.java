package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.ModelException;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.course.desired.UpdateDesiredCoursePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateDesiredCourse implements UpdateDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;
    private final CustomerRepository customerRepository;

    public UpdateDesiredCourse(DesiredCourseRepository desiredCourseRepository,
                               CustomerRepository customerRepository) {
        this.desiredCourseRepository = desiredCourseRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public void updateDesiredCourse(String idCustomer, DesiredCourse desiredCourse){

        Optional<CustomerModel> customerModel = customerRepository.findByIdCustomer(idCustomer);

        if(customerModel.isEmpty()){
            throw new ModelException("Customer not found -  Customer " + idCustomer +"!");
        }

        var desiredCourseModel = DesiredCourseConverter.toModel(customerModel.get(),desiredCourse);

        desiredCourseRepository.save(desiredCourseModel);
    }
}
