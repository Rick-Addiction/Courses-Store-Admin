package com.coursesstore.admin.core.domain;

import com.coursesstore.admin.adapters.database.course.CourseModel;
import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import com.coursesstore.admin.adapters.database.customer.model.CustomerModel;
import com.coursesstore.admin.adapters.database.teacher.model.TeacherModel;
import com.coursesstore.admin.adapters.http.customer.post.dto.RequestPostAcquiredCourseByCustomer;
import com.coursesstore.admin.adapters.http.customer.post.dto.RequestPostDesiredCourseByCustomer;
import com.coursesstore.admin.adapters.http.course.put.dto.RequestPutCourse;
import com.coursesstore.admin.adapters.http.customer.post.dto.RequestPostCustomer;
import com.coursesstore.admin.adapters.http.customer.put.dto.RequestPutCustomer;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.domain.teacher.Teacher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

public class DomainUtils {

    public static Customer generateCustomer(){
        Customer customer = new Customer();
        customer.setIdCustomer(UUID.randomUUID());
        customer.setFirstname("Dina");
        customer.setLastname("Laster");
        customer.setPhone("+55 11 99999-9999");
        customer.setEmail("email_test@testdomain.com");
        customer.setCompany("Robots with Love");
        customer.setLinkedIn("linkedIn.com/DinaLaster");
        customer.setPosition("CEO");

        return customer;
    }

    public static CustomerModel generateCustomerModel(){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setIdCustomer(String.valueOf(UUID.randomUUID()));
        customerModel.setFirstname("Dina");
        customerModel.setLastname("Laster");
        customerModel.setPhone("+55 11 99999-9999");
        customerModel.setEmail("email_test@testdomain.com");
        customerModel.setCompany("Robots with Love");
        customerModel.setPosition("CEO");

        return customerModel;

    }

    public static Course generateCourse(Teacher teacher){
        Course course = new Course();
        course.setIdCourse(UUID.randomUUID());
        course.setName("Python Advanced");
        course.setOriginalValue(BigDecimal.valueOf(100.50f).setScale(2));
        course.setTeacherResponsible(teacher);

        return course;
    }

    public static Course generateCourse(){
        Course course = new Course();
        course.setIdCourse(UUID.randomUUID());
        course.setName("Python Advanced");
        course.setOriginalValue(BigDecimal.valueOf(100.50f).setScale(2));
        course.setTeacherResponsible(generateTeacher());

        return course;
    }

    public static CourseModel generateCourseModel(){
        CourseModel courseModel = new CourseModel();
        courseModel.setIdCourse(String.valueOf(UUID.randomUUID()));
        courseModel.setName("Python Advanced");
        courseModel.setOriginalValue(BigDecimal.valueOf(100.50f).setScale(2));
        courseModel.setTeacherResponsible(generateTeacherModel());

        return courseModel;
    }

    public static Customer generateCustomerWithAnAcquiredCourse(){
        Customer customer = DomainUtils.generateCustomer();

        AcquiredCourse acquiredCourse = new AcquiredCourse();
        acquiredCourse.setIdAcquiredCourse(UUID.randomUUID());
        acquiredCourse.setValuePaid(BigDecimal.valueOf(50.50f).setScale(2));
        acquiredCourse.setAcquisitionDate(LocalDate.of(2021,03,27));
        acquiredCourse.setCourse(generateCourse());

        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        return customer;
    }

    public static Customer generateCustomerWithAnAcquiredCourse(Customer customer, Course course){
        AcquiredCourse acquiredCourse = new AcquiredCourse();
        acquiredCourse.setIdAcquiredCourse(UUID.randomUUID());
        acquiredCourse.setValuePaid(BigDecimal.valueOf(50.50f).setScale(2));
        acquiredCourse.setAcquisitionDate(LocalDate.of(2021,03,27));
        acquiredCourse.setCourse(course);

        customer.setAcquiredCourses(new HashSet<>());
        customer.getAcquiredCourses().add(acquiredCourse);

        return customer;
    }

    public static AcquiredCourseModel generateAcquiredCourseModel(){
        AcquiredCourseModel acquiredCourseModel = new AcquiredCourseModel();
        acquiredCourseModel.setValuePaid(BigDecimal.valueOf(50.50f).setScale(2));
        acquiredCourseModel.setAcquisitionDate(LocalDate.of(2021,03,27));
        acquiredCourseModel.setCourse(generateCourseModel());
        acquiredCourseModel.setCustomer(generateCustomerModel());

        return acquiredCourseModel;
    }

    public static Customer generateCustomerWithADesiredCourse(){
        Customer customer = generateCustomer();

        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setIdDesiredCourse(UUID.randomUUID());
        desiredCourse.setDesireDescription("He asked for a discount");
        desiredCourse.setDesireDate(LocalDate.of(2021,03,27));
        desiredCourse.setCourse(generateCourse());

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

    public static Customer generateCustomerWithADesiredCourse(Customer customer, Course course){
        DesiredCourse desiredCourse = new DesiredCourse();
        desiredCourse.setIdDesiredCourse(UUID.randomUUID());
        desiredCourse.setDesireDescription("He asked for a discount");
        desiredCourse.setDesireDate(LocalDate.of(2021,03,27));
        desiredCourse.setCourse(course);

        customer.setDesiredCourses(new HashSet<>());
        customer.getDesiredCourses().add(desiredCourse);

        return customer;
    }

    public static DesiredCourseModel generateDesiredCourseModel(){
        DesiredCourseModel desiredCourseModel = new DesiredCourseModel();
        desiredCourseModel.setDesireDescription("He asked for a discoule");
        desiredCourseModel.setDesireDate(LocalDate.of(2021,03,27));
        desiredCourseModel.setCourse(generateCourseModel());
        desiredCourseModel.setCustomer(generateCustomerModel());

        return desiredCourseModel;
    }

    public static Teacher generateTeacher(UUID idTeacher){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(idTeacher);
        teacher.setName("Joel");

        return teacher;
    }

    public static Teacher generateTeacher(){
        Teacher teacher = new Teacher();
        teacher.setIdTeacher(UUID.randomUUID());
        teacher.setName("Joel");

        return teacher;
    }

    public static TeacherModel generateTeacherModel(){
        TeacherModel teacher = new TeacherModel();
        teacher.setIdTeacher(String.valueOf(UUID.randomUUID()));
        teacher.setName("Joel");

        return teacher;

    }

    public static RequestPostCustomer generateRequestPostCustomer(){

        RequestPostCustomer requestPostCustomer = new RequestPostCustomer();
        requestPostCustomer.setFirstname("Dina");
        requestPostCustomer.setLastname("Laster");
        requestPostCustomer.setEmail("email_test@testdomain.com");
        requestPostCustomer.setCompany("Robots with Love");
        requestPostCustomer.setEmail("linkedIn.com/DinaLaster");
        requestPostCustomer.setPhone("+55 11 99999-9999");
        requestPostCustomer.setPosition("CEO");

        return requestPostCustomer;
    }

    public static RequestPutCustomer generateRequestPutCustomer(String idCustomer){

        RequestPutCustomer requestPutCustomer = new RequestPutCustomer();
        requestPutCustomer.setIdCustomer(idCustomer);
        requestPutCustomer.setFirstname("Dina");
        requestPutCustomer.setLastname("Laster");
        requestPutCustomer.setEmail("email_test@testdomain.com");
        requestPutCustomer.setCompany("Robots with Love");
        requestPutCustomer.setEmail("linkedIn.com/DinaLaster");
        requestPutCustomer.setPhone("+55 11 99999-9999");
        requestPutCustomer.setPosition("CEO");

        return requestPutCustomer;
    }

    public static RequestPutCourse generateRequestPutCourse(String idCourse, String idTeacher){
        RequestPutCourse requestPutCourse = new RequestPutCourse();
        requestPutCourse.setIdCourse(idCourse);
        requestPutCourse.setName("Python Advanced");
        requestPutCourse.setOriginalValue("100.50");
        requestPutCourse.setIdTeacherResponsible(idTeacher);

        return requestPutCourse;
    }

    public static RequestPostAcquiredCourseByCustomer generateRequestPostAcquiredCourse(String idCourse){

        RequestPostAcquiredCourseByCustomer requestPostAcquiredCourseByCustomer = new RequestPostAcquiredCourseByCustomer();
        requestPostAcquiredCourseByCustomer.setIdCourse(idCourse);
        requestPostAcquiredCourseByCustomer.setValuePaid("50.50");
        requestPostAcquiredCourseByCustomer.setAcquisitionDate("01/01/2021");

        return requestPostAcquiredCourseByCustomer;
    }

    public static RequestPostDesiredCourseByCustomer generateRequestPostDesiredCourse(String idCourse){

        RequestPostDesiredCourseByCustomer requestPostDesiredCourseByCustomer = new RequestPostDesiredCourseByCustomer();
        requestPostDesiredCourseByCustomer.setIdCourse(idCourse);
        requestPostDesiredCourseByCustomer.setDesireDescription("He asked for a discount");
        requestPostDesiredCourseByCustomer.setDesireDate("01/01/2021");

        return requestPostDesiredCourseByCustomer;
    }



}
