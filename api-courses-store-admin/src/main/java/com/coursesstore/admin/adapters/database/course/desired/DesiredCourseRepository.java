package com.coursesstore.admin.adapters.database.course.desired;

import com.coursesstore.admin.adapters.database.course.acquired.model.AcquiredCourseModel;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseKey;
import com.coursesstore.admin.adapters.database.course.desired.model.DesiredCourseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DesiredCourseRepository extends CrudRepository<DesiredCourseModel, DesiredCourseKey> {

    @Query(
            value = "SELECT * FROM TB_DESIRED_COURSE u WHERE u.CUSTOMER_ID = :customerId",
            nativeQuery = true)
    Iterable<DesiredCourseModel> findByCustomerId(@Param("customerId") String customerId);


}