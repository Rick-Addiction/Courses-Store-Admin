package com.coursesstore.admin.adapters.http.customer.get;

import com.coursesstore.admin.adapters.http.customer.get.dto.GetCustomerConverter;
import com.coursesstore.admin.adapters.http.customer.get.dto.ResponseGetAcquiredCoursesByCustomer;
import com.coursesstore.admin.adapters.http.customer.get.dto.ResponseGetCustomer;
import com.coursesstore.admin.adapters.http.customer.get.dto.ResponseGetDesiredCoursesByCustomer;
import com.coursesstore.admin.core.domain.course.Course;
import com.coursesstore.admin.core.domain.course.acquired.AcquiredCourse;
import com.coursesstore.admin.core.domain.course.desired.DesiredCourse;
import com.coursesstore.admin.core.domain.customer.Customer;
import com.coursesstore.admin.core.usecases.course.acquired.SearchForAcquiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.course.acquired.SearchForNotAcquiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.SearchForDesiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.course.desired.SearchForNotDesiredCoursesByCustomer;
import com.coursesstore.admin.core.usecases.customer.SearchForAllCustomers;
import com.coursesstore.admin.core.usecases.customer.SearchForCustomer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courses-store/customer")
public class GetCustomerController {

    private final SearchForAllCustomers searchForAllCustomers;
    private final SearchForCustomer searchForCustomer;
    private final SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer;
    private final SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer;
    private final SearchForNotDesiredCoursesByCustomer searchForNotDesiredCoursesByCustomer;
    private final SearchForNotAcquiredCoursesByCustomer searchForNotAcquiredCoursesByCustomer;

    public GetCustomerController(SearchForAllCustomers searchForAllCustomers,
                                 SearchForCustomer searchForCustomer,
                                 SearchForDesiredCoursesByCustomer searchForDesiredCoursesByCustomer,
                                 SearchForAcquiredCoursesByCustomer searchForAcquiredCoursesByCustomer,
                                 SearchForNotDesiredCoursesByCustomer searchForNotDesiredCoursesByCustomer,
                                 SearchForNotAcquiredCoursesByCustomer searchForNotAcquiredCoursesByCustomer) {
        this.searchForAllCustomers = searchForAllCustomers;
        this.searchForCustomer=searchForCustomer;
        this.searchForAcquiredCoursesByCustomer = searchForAcquiredCoursesByCustomer;
        this.searchForDesiredCoursesByCustomer = searchForDesiredCoursesByCustomer;
        this.searchForNotDesiredCoursesByCustomer=searchForNotDesiredCoursesByCustomer;
        this.searchForNotAcquiredCoursesByCustomer=searchForNotAcquiredCoursesByCustomer;
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseGetCustomer> getAllCustomers() {

        List<Customer> listCustomers = searchForAllCustomers.execute("");

        var responseGetCustomer =
                GetCustomerConverter.toResponseGetCustomer(listCustomers);

        return ResponseEntity.ok(responseGetCustomer);
    }

    @GetMapping("/search/{id_customer}")
    public ResponseEntity<ResponseGetCustomer> getCustomer(@PathVariable(value = "id_customer") String idCustomer) {

        Customer customer = searchForCustomer.execute(idCustomer);

        var responseGetCustomer =
                GetCustomerConverter.toResponseGetCustomer(customer);

        return ResponseEntity.ok(responseGetCustomer);
    }

    @GetMapping("/{id_customer}/acquired-courses")
    public ResponseEntity<ResponseGetAcquiredCoursesByCustomer> getAcquiredCourseByCustomer(@PathVariable(value = "id_customer") String idCustomer) {

        List<AcquiredCourse> listAcquiredCourses = searchForAcquiredCoursesByCustomer.execute(idCustomer);
        List<Course> listNotAcquiredCourses = searchForNotAcquiredCoursesByCustomer.execute(idCustomer);

        var responseGetAcquiredCoursesByCustomer =
                GetCustomerConverter.toResponseGetAcquiredCoursesByCustomer(listAcquiredCourses,listNotAcquiredCourses);

        return ResponseEntity.ok(responseGetAcquiredCoursesByCustomer);
    }

    @GetMapping("/{id_customer}/desired-courses")
    public ResponseEntity<ResponseGetDesiredCoursesByCustomer> getDesiredCourseByCustomer(@PathVariable(value = "id_customer") String idCustomer) {

        List<DesiredCourse> listDesiredCourses = searchForDesiredCoursesByCustomer.execute(idCustomer);
        List<Course> listNotDesiredCourses = searchForNotDesiredCoursesByCustomer.execute(idCustomer);

        var responseGetDesiredCoursesByCustomer =
                GetCustomerConverter.toResponseGetDesiredCoursesByCustomer(listDesiredCourses,listNotDesiredCourses);

        return ResponseEntity.ok(responseGetDesiredCoursesByCustomer);
    }

}
