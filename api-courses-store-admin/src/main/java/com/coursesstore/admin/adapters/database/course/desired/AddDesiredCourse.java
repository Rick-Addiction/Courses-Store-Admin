package com.coursesstore.admin.adapters.database.course.desired;



import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseConverter;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.core.domain.course.desired.AddDesiredCoursePort;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.adapters.database.course.desired.exception.DesiredCourseConflictException;
import com.coursesstore.admin.core.domain.customer.Customer;
import org.springframework.stereotype.Component;

@Component
public class AddDesiredCourse implements AddDesiredCoursePort {

    private final DesiredCourseRepository desiredCourseRepository;

    public AddDesiredCourse(DesiredCourseRepository desiredCourseRepository){
        this.desiredCourseRepository=desiredCourseRepository;
    }

    @Override
    public void addNewDesiredCourseByCustomer(Customer customer) {
        try {
            DesiredCourseModel desiredCourseModel = DesiredCourseConverter.toModel(customer);
            desiredCourseRepository.save(desiredCourseModel);
        } catch (Exception ex) {
            throw new DesiredCourseConflictException();
        }
    }
}
