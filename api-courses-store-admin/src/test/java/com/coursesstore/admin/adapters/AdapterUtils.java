package com.coursesstore.admin.adapters;

import com.coursesstore.admin.adapters.database.course.CourseRepository;
import com.coursesstore.admin.adapters.database.course.CreateCourse;
import com.coursesstore.admin.adapters.database.course.acquired.AcquiredCourseRepository;
import com.coursesstore.admin.adapters.database.course.acquired.AddAcquiredCourse;
import com.coursesstore.admin.adapters.database.course.desired.AddDesiredCourse;
import com.coursesstore.admin.adapters.database.course.desired.DesiredCourseRepository;
import com.coursesstore.admin.adapters.database.customer.CreateCustomer;
import com.coursesstore.admin.adapters.database.customer.CustomerRepository;
import com.coursesstore.admin.adapters.database.teacher.CreateTeacher;
import com.coursesstore.admin.adapters.database.teacher.TeacherRepository;
import com.coursesstore.admin.core.domain.DomainUtils;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdapterUtils {

    private static CustomerRepository customerRepository;
    private static CourseRepository courseRepository;
    private static TeacherRepository teacherRepository;
    private static AcquiredCourseRepository acquiredCourseRepository;
    private static DesiredCourseRepository desiredCourseRepository;

    @Autowired
    private void initStaticDao (CustomerRepository customerRepository,
                                CourseRepository courseRepository,
                                TeacherRepository teacherRepository,
                                AcquiredCourseRepository acquiredCourseRepository,
                                DesiredCourseRepository desiredCourseRepository) {
       this.customerRepository=customerRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.acquiredCourseRepository=acquiredCourseRepository;
        this.desiredCourseRepository=desiredCourseRepository;
    }


    public static Customer registerANewCustomer(){
        Customer customer = DomainUtils.generateCustomer();
        CreateCustomer createCustomer = new CreateCustomer(customerRepository);
        createCustomer.createCustomer(customer);

        return customer;
    }

    public static Course registerANewCourse(Teacher teacher){
        Course newCourse = DomainUtils.generateCourse(teacher);
        CreateCourse createCourse = new CreateCourse(courseRepository);
        createCourse.createCourse(newCourse);

        return newCourse;
    }

    public static Customer registerANewAcquiredCourse(Customer customer, Course course){
        customer = DomainUtils.generateCustomerWithAnAcquiredCourse(customer, course);
        AddAcquiredCourse addAcquiredCourse = new AddAcquiredCourse(acquiredCourseRepository,courseRepository);
        addAcquiredCourse.addNewAcquiredCourseByCustomer(customer);

        return customer;
    }

    public static Customer registerANewDesiredCourse(Customer customer, Course course){
        customer = DomainUtils.generateCustomerWithADesiredCourse(customer, course);
        AddDesiredCourse addDesiredCourse = new AddDesiredCourse(desiredCourseRepository,courseRepository);
        addDesiredCourse.addNewDesiredCourseByCustomer(customer);

        return customer;
    }


    public static Teacher registerANewTeacher(){
        Teacher newTeacher = DomainUtils.generateTeacher();
        CreateTeacher createTeacher = new CreateTeacher(teacherRepository);
        createTeacher.createTeacher(newTeacher);

        return newTeacher;
    }





    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
